from flask import Flask
from flask_restful import Resource, Api, reqparse, abort, marshal, fields
import json

app = Flask(__name__)
api = Api(app)

with open('config.json', 'r') as f:
    config = json.load(f)

cards = {}  # this is our mock database


def startup():
    global cards
    with open(config['cards_location']) as _f:
        cards = json.load(_f)


cardFields = {
    "id": fields.Integer,
    "name": fields.String,
    "qty": fields.Integer
}

collectionFields = {
    "cards": fields.Nested(cardFields)
}


class Collection(Resource):
    global cards

    def __init__(self):
        super(Collection, self).__init__()

    def get(self):
        return {'collection': cards}, 200
        # else:
        #     abort(404)


class Cards(Resource):
    global cards  # bad practice but for demo purposes...

    def __init__(self):
        super(Cards, self).__init__()

    def save_db(self):
        with open(config['cards_location'], 'w') as _f:
            json.dump(cards, _f)
            print("saved db")

    def get(self):
        parser = reqparse.RequestParser()
        parser.add_argument('id', required=True)
        args = parser.parse_args()
        if args['id'] in cards:
            card = cards[args['id']]
            return {'card': marshal(card, cardFields)}, 200
        else:
            abort(404)

    def post(self):
        parser = reqparse.RequestParser()
        parser.add_argument('id', required=True)
        parser.add_argument("name", required=True, type=str)
        parser.add_argument("qty", required=True, type=int)
        args = parser.parse_args()
        if args['id'] in cards:
            return {
                       'message': f"Card #{args['id']} already exists."
                   }, 401
        card = {}
        for k, v in args.items():
            if v is not None:
                print(k)
                card[k] = v
        cards[args['id']] = card
        self.save_db()
        return "Card added.", 200

    def put(self):
        parser = reqparse.RequestParser()
        parser.add_argument('id', required=True)
        parser.add_argument("name", required=False, type=str)
        parser.add_argument("qty", required=False, type=int)
        args = parser.parse_args()
        if args['id'] not in cards:
            return {
                       'message': f"'{args['id']}' does not exist."
                   }, 404
        card = {}
        for k, v in args.items():
            if v is not None:
                cards[args['id']][k] = v
        self.save_db()
        return "Card updated", 200

    def delete(self):
        parser = reqparse.RequestParser()
        parser.add_argument('id', required=True)
        args = parser.parse_args()
        if args['id'] not in cards:
            return {
                       'message': f"'{args['id']}' does not exist."
                   }, 404
        cards.pop(args['id'])
        self.save_db()
        return "Card deleted", 200


api.add_resource(Collection, "/")
api.add_resource(Cards, "/cards")

if __name__ == "__main__":
    startup()
    app.run(debug=True)
