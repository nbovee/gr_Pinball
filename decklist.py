from flask import Flask
from flask_restful import Resource, Api, reqparse, abort, marshal, fields
import json

app = Flask(__name__)
api = Api(app)

with open('config.json', 'r') as f:
    config = json.load(f)

# with open("decklist.json", "r") as f:  # modify this later to accept filepath for unit tests
#     deck = json.load(f)


cardFields = {  # there are more but these are what we care about. Also might need to load these differently too?
    "id": fields.Integer,
    "name": fields.String,
    "qty": fields.Integer
}


class Card(Resource):
    with open(config['cards_location']) as f:
        cards = json.load(f) #all cards know about each other to avoid collision

    def __init__(self):
        # self.reqparse = reqparse.RequestParser()
        # self.reqparse.add_argument("id", type=int, location="json")
        # self.reqparse.add_argument("name", type=str, location="json")
        # self.reqparse.add_argument("price", type=int, location="json")
        super(Card, self).__init__()

    def inCollection(self, _id):
        card = Card.cards[_id]
        if card is not None:
            return True if card['qty'] > 0 else False
        else:
            return False

    def get(self):
        parser = reqparse.RequestParser()
        parser.add_argument('id', required=True)
        args = parser.parse_args()

        return {'card': marshal(card, cardFields)}, 200

    def post(self):
        parser = reqparse.RequestParser()
        parser.reqparse.add_argument('id', required=True)
        parser.reqparse.add_argument("name", required=True, type=str)
        parser.reqparse.add_argument("price", required=False, type=int)
        parser.reqparse.add_argument("qty", required=False, type=int)
        args = parser.parse_args()
        if Card.cards[args['id']] is not None:
            return {
                       'message': f"'{args['id']}' already exists."
                   }, 401
        card = {}
        for k, v in args.items():
            if v is not None:
                card[k] = v
        Card.cards['id'] = card
        return {}, 200


    # def put(self, id):
    #     card = [card for card in deck if card['id'] == id]
    #     if len(card) == 0:
    #         abort(404)
    #     card = card[0]
    #     args = self.reqparse.parse_args()
    #     for k, v in args.items():
    #         if v is not None:
    #             card[k] = v
    #     return {"card": marshal(card, cardFields)}
    #
    # def delete(self, id):
    #     card = [card for card in deck if card['id'] == id]
    #     if len(card) == 0:
    #         abort(404)
    #     deck.remove(card[0])
    #     return 201


api.add_resource(Card, "/card/")

if __name__ == "__main__":
    app.run(debug=True)
