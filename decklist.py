from flask import Flask
from flask_restful import Resource, Api, reqparse, abort, marshal, fields
import json

app = Flask(__name__)
api = Api(app)

with open('config.json', 'w') as f:
    config = json.load(f)

with open("decklist.json", "r") as f:  # modify this later to accept filepath for unit tests
    deck = json.load(f)

# overwrite imported deck for initial testing


cardFields = {  # there are more but these are what we care about. Also might need to load these differently too?
    "id": fields.Integer,
    "name": fields.String,
    "prices": fields.Integer  # fields.Nested({"usd": fields.String}) #change to val in cents?
}


class Card(Resource):
    def __init__(self):
        self.reqparse = reqparse.RequestParser()
        self.reqparse.add_argument("id", type=int, location="json")
        self.reqparse.add_argument("name", type=str, location="json")
        self.reqparse.add_argument("prices", type=int, location="json")

    def get(self, id):
        card = [card for card in deck if card['id'] == id]
        if len(card) == 0:
            abort(404)
        return {"card": marshal(card[0], cardFields)}

    def put(self, id):
        card = [card for card in deck if card['id'] == id]
        if len(card) == 0:
            abort(404)
        card = card[0]
        args = self.reqparse.parse_args()
        for k, v in args.items():
            if v is not None:
                card[k] = v
        return {"card": marshal(card, cardFields)}

    def delete(self, id):
        card = [card for card in deck if card['id'] == id]
        if len(card) == 0:
            abort(404)
        deck.remove(card[0])
        return 201


if __name__ == "__main__":
    app.run(port=5001, debug=True)
