import requests
# simply ask the decklist api for a dict, and iterate over it with pricer requests
import json
import time
import os

dirname = os.path.split(os.path.realpath(__file__))
print(dirname[0])
with open(os.path.join(dirname[0], 'config.json'), 'r') as f:
    config = json.load(f)
    deck_db = config['card_db']
    pricer = config['card_pricer']


def main():
    deck = requests.get(deck_db).json()
    total_price = 0
    for k, v in deck['collection'].items():
        temp = requests.get(pricer + "?name=" + v['name']).json()
        print(temp)
        temp = temp['card']['price']
        if temp is not None:
            total_price += temp
        time.sleep(0.1)  # rate limit
    print(f"Total price of provided deck is ${total_price}.")


if __name__ == "__main__":
    main()
