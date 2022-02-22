FROM python:3.7-slim-buster


COPY seed.py seed.py
COPY data data
ADD requirements.txt requirements.txt
RUN pip3 install -r requirements.txt

CMD ["python3","seed.py" ]
