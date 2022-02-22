import pandas as pd
import socket
import os
import time
import psycopg2

HOST =  os.environ["HOST"]
PORT = os.environ["PORT"]
DATABASE = os.environ["DATABASE"]
USERNAME = os.environ["PG_USER"]
PASSWORD = os.environ["PG_PASSWORD"]

def get_connection():
	try:
		connection = psycopg2.connect(host=HOST,
										database=DATABASE,user=USERNAME,password=PASSWORD,port=PORT)
		if not connection.closed:
			db_Info = connection.server_version
			print("Connected to PostgreSQL Server version ", db_Info)
			return connection
	except Exception as e:
		print("Error while connecting to MySQL", e)

def make_query_params(cols_order,rows):
		ordered_rows = []
		for row in rows:
			ordered_rows.append(tuple([row[col] for col in cols_order]))
		return ordered_rows

def insert(conn,query,values):
	cursor = conn.cursor()
	try:
		print("INSERTING SCHOOLS.")
		for value in values:
			cursor.execute(query,value)
		conn.commit()
		print('DONE INSERTING SCHOOLS.')
		
	except Exception as e :
		print("FAILED TO INSERT SCHOOLS.")
		print(e)

def seed_schools(path,conn):
	values = []
	df = pd.read_csv(path,dtype=str)
	df.fillna('', inplace=True)
	columns = ["Id","Name","Type","Province","Suburb","Postal_Code"]
	for _,row in df.iterrows():
		value = tuple([row[c] for c in columns])
		values.append(value)		
	query = """INSERT INTO school(school_id,name,type,province,suburb,postal_code) values (%s,%s,%s,%s,%s,%s)"""
	insert(conn,query,values)


def read_pdf(path):
	with open(path,'rb') as f:
		pdf = f.read()
	return pdf

def get_doc_info(name):
	grade,subject,p_no,month,year,stage,type_,province = name.split('-')
	date = '/'.join(['01',month,year])
	data = {'grade':grade,'subject':subject,'paper_no':p_no,'date':date,
	'type':stage,'province':province}
	cols = (grade,subject,p_no,stage,province,date)
	return cols


def insert_paper(cursor,query,value):
	try:
		cursor.execute(query,value)
	except Exception as e:
		print(e)

def seed_papers(conn,path):
	pquery = """INSERT INTO resource.past_paper(grade,subject,paper_no,
								 type,province,date,paper_name,paper_url) VALUES(%s,%s,%s,%s,%s,%s,%s,%s) RETURNING paper_id"""
	mquery = """INSERT INTO resource.past_paper_memo(grade,subject,paper_no,
								 type,province,date,memo_name,memo_url,paper_id) VALUES(%s,%s,%s,%s,%s,%s,%s,%s,%s)"""
	cursor = conn.cursor()
	df = pd.read_csv(path)
	print("INSERTING PAST PAPERS.")
	for _,row in df.iterrows():
		paper_name = row['paper_name']
		paper_url = row['paper_url']
		memo_name = row['memo_name']
		memo_url = row['memo_url']
		x = paper_name.replace('.pdf','')
		y = memo_name.replace('.pdf','')
		paper_info = get_doc_info(x)
		paper_info = paper_info + (paper_name,paper_url)
		insert_paper(cursor,pquery,paper_info)
		paper_id = cursor.fetchone()
		memo_info = get_doc_info(y)
		memo_info = memo_info + (memo_name,memo_url,paper_id[0])
		insert_paper(cursor,mquery,memo_info)
	conn.commit()
	print("DONE INSERTING PAST PAPERS.")


if __name__=="__main__":
	conn = get_connection()
	s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
	start = time.time()
	while True:
			try:
				s.connect(('postgres', 5432))
				conn = get_connection()
				seed_schools('data/output/schools.csv',conn)
				seed_papers(conn,'data/output/past_papers.csv')
				break
			except socket.error as ex:
					print("WAITING FOR POSTGRES")
					time.sleep(0.1)
			if time.time()-start>60:
				print("TIME OUT POSTGRESS NOT STARTED")
				break
