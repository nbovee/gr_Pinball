FROM python:3.8
WORKDIR /card_collection
ADD . /card_collection
RUN pip install -r requirements.txt
EXPOSE 5000
CMD ["python","card_collection.py"]