import requests
# simply ask the decklist api for the current market price of a list
import json

with open('config.json', 'w') as f:
    config = json.load(f)
