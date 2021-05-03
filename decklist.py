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


class Cards(Resource):
    with open(config['cards_location']) as f:
        cards = json.load(f)  # all cards know about each other to avoid collision

    def __init__(self):
        # self.reqparse = reqparse.RequestParser()
        # self.reqparse.add_argument("id", type=int, location="json")
        # self.reqparse.add_argument("name", type=str, location="json")
        # self.reqparse.add_argument("price", type=int, location="json")
        super(Cards, self).__init__()

    def in_collection(self, _id):
        card = Cards.cards[_id]
        if card is not None:
            return True if card['qty'] > 0 else False
        else:
            return False

    def get(self):
        parser = reqparse.RequestParser()
        parser.add_argument('id', required=True)
        args = parser.parse_args()
        if args['id'] in Cards.cards:
            card = Cards.cards[args['id']]
            return {'card': marshal(card, cardFields)}, 200
        else:
            abort(404)

    def post(self):
        parser = reqparse.RequestParser()
        parser.add_argument('id', required=True)
        parser.add_argument("name", required=True, type=str)
        parser.add_argument("qty", required=True, type=int)
        args = parser.parse_args()
        if args['id'] in Cards.cards:
            return {
                       'message': f"Card #{args['id']} already exists."
                   }, 401
        card = {}
        for k, v in args.items():
            if v is not None:
                print(k)
                card[k] = v
        Cards.cards[args['id']] = card
        return "Card added.", 200

    def put(self):
        parser = reqparse.RequestParser()
        parser.add_argument('id', required=True)
        parser.add_argument("name", required=False, type=str)
        parser.add_argument("qty", required=False, type=int)
        args = parser.parse_args()
        if args['id'] not in Cards.cards:
            return {
                       'message': f"'{args['id']}' does not exist."
                   }, 404
        card = {}
        for k, v in args.items():
            if v is not None:
                Cards.cards[args['id']][k] = v
        return "Card updated", 200

    def delete(self):
        parser = reqparse.RequestParser()
        parser.add_argument('id', required=True)
        args = parser.parse_args()
        if args['id'] not in Cards.cards:
            return {
                       'message': f"'{args['id']}' does not exist."
                   }, 404
        Cards.cards.pop(args['id'])
        return "Card deleted", 200


api.add_resource(Cards, "/cards")

if __name__ == "__main__":
    app.run(debug=True)
