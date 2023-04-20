FROM python:3.8
WORKDIR /scryfall
ADD . /scryfall
RUN pip install -r requirements.txt
EXPOSE 5000
CMD ["python","scryfall_wrapper.py"]