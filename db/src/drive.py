from webbrowser import get
from pydrive.auth import GoogleAuth
from pydrive.drive import GoogleDrive
import pandas as pd

gauth = GoogleAuth()
gauth.LocalWebserverAuth()

drive = GoogleDrive(gauth)

def assign_link(file):
    keys = file.keys()
    if 'alternateLink' in keys:
        link = file['alternateLink']
        return link

def get_children(root_folder_id):
    query = "\'" + root_folder_id + "\'" + " in parents and trashed=false"
    file_list = drive.ListFile({'q': query}).GetList()
    formated_list = []
    for file in file_list:
        new_file = {'id':file['id'],'link':assign_link(file),'mimeType':file['mimeType'],
        'title':file['title']}
        formated_list.append(new_file)
    return formated_list

def get_folder_id(root_folder_id,root_folder_title):
    file_list = get_children(root_folder_id)
    for file in file_list:
        if(file['title'] == root_folder_title):
            return file['id']

def get_sub_folders(folder_id):
    subfiles = get_children(folder_id)
    subfolders = []
    for sub in subfiles:
        if sub['mimeType'] == 'application/vnd.google-apps.folder':
            subfolders.append({"title":sub['title'],"id":sub['id']})
    return subfolders

def get_folder_files(data,folder,folder_id):
    sub_folders = get_sub_folders(folder_id)
    if len(sub_folders)==0:
        items = get_children(folder_id)
        data[folder].extend(items)
    else:
        for sub_folder in sub_folders:
            get_folder_files(data,sub_folder['title'],sub_folder['id'])
        return

def structure_data(data,subject,sample):
    papers = sample['paper']
    memoranda = sample['memo']
    for i in range(len(papers)):
        paper_title = papers[i]['title']
        paper_link = papers[i]['link']
        memo_title = memoranda[i]['title']
        memo_link = memoranda[i]['link']
        data['subject'].append(subject)
        data['paper_name'].append(paper_title)
        data['paper_url'].append(paper_link)
        data['memo_name'].append(memo_title)
        data['memo_url'].append(memo_link)
    

root_id = '1P9iJ1vXNjqCxBeC7rF5KLSkOocu2TGxw'
subjects = ['mathematics','physics']
data = {'subject':[],'paper_name':[],'memo_name':[],'paper_url':[],'memo_url':[]}
for subject in subjects:
    subject_folder_id = get_folder_id(root_id,subject)
    sample = {'memo':[],'paper':[]}
    get_folder_files(sample,subject,subject_folder_id)
    structure_data(data,subject,sample)

df = pd.DataFrame(data)
df.to_csv('data/output/past_papers.csv',index=False)