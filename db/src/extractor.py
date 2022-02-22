import numpy as np
import pandas as pd
import glob
import re

info_cols_raw = ['NatEmis','Institution_Name','Status','Sector','Type_PED','Phase_PED','Specialization','ExamNo']
info_cols_map = {'NatEmis':'Id','Institution_Name':'Name','Status':'Status','Sector':'Sector','Type_PED':'Type',
                           'Phase_PED':'Phase','Specialization':'Specialization',
                           'ExamNo':'ExamNo'}
info_cols = list(info_cols_map.values())
loc_cols_raw = ['Province','DistrictMunicipalityName','Local MunicipalityName','Suburb',
                              'PostalAddress']
loc_cols_map = {'Province':'Province','DistrictMunicipalityName' : 'District_Municipality',
                      'Local MunicipalityName':'Local_Municipality','Suburb':'Suburb',
                      'PostalAddress':'Postal_Code'}
loc_cols = list(loc_cols_map.values())


def load_data(path):
    dfs = []
    fnames = glob.glob(path)
    for fname in fnames:
        df = pd.read_excel(fname)
        dfs.append(df)
    return dfs

def filter_merge(dfs):
    columns = info_cols_raw + loc_cols_raw
    new_dfs = []
    for df in dfs:
        new_dfs.append(df[columns])
    
    df =  pd.concat(new_dfs,ignore_index=True).copy()
    c = {**info_cols_map,**loc_cols_map}
    df.rename(columns = c,inplace=True)
    df = df.drop_duplicates(subset='Id', keep='first')
    return df

def clean(df,cols):
    def f(x):
        y = re.sub(r"[^a-zA-Z0-9]+", ' ', str(x)).upper()
        return y.strip()
    def g(x):
        data = re.split(' |,',str(x))
        if len(data)>1:
            if data[-1].strip().isnumeric():
                return data[-1].strip()
            return ''
        return ''
    
    df_c = df.copy().fillna('')
    for c in cols:
        if c!='Postal_Code':
            df_c[c] = df_c[c].apply(f)
        else:
             df_c[c] = df_c[c].apply(g)
    return df_c

def process_files(path):
    dfs = load_data(path)
    df = filter_merge(dfs)
    df_c = clean(df,info_cols + loc_cols)
    return df_c


if __name__=="__main__":
    df = process_files('data/input/*.xlsx')
    df.to_csv('data/output/schools.csv',index=False)