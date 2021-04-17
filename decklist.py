# patterned after code from https://github.com/umermansoor/microservices/ as I learn the python/flask framework

from services import root_dir, nice_json
from flask import Flask
from werkzeug.exceptions import NotFound
import json

app = Flask(__name__)

with open("{}/database/decklist.json".format(root_dir()),
          "r") as f:  # modify this later to accept filepath for unit tests
    deck = json.load(f)


@app.route("/", methods=['GET'])
def hello():
    return nice_json({
        "uri": "/",
        "subresource_uris": {
            "deck": "/cards",
            "cards": "/cards/<id>"
        }
    })


@app.route("/Deck/<cardid>", methods=['GET'])
def movie_info(cardid):
    if cardid not in deck:
        raise NotFound

    result = deck[cardid]
    result["uri"] = "/deck/{}".format(cardid)

    return nice_json(result)


@app.route("/card", methods=['GET'])
def card_record():
    return nice_json(card)


if __name__ == "__main__":
    app.run(port=5001, debug=True)
