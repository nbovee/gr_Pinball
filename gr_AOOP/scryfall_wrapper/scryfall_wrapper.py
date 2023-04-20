from flask import Flask
from flask_restful import Resource, Api, reqparse, abort, marshal, fields
import requests
import time

app = Flask(__name__)
api = Api(app)

cardFields = {
    "name": fields.String,
    "price": fields.Float
}


class Price(Resource):

    def __init__(self):
        super(Price, self).__init__()

    def get(self):
        parser = reqparse.RequestParser()
        parser.add_argument('name', required=True)
        args = parser.parse_args()
        card = {}
        card['name'] = args['name']
        response = requests.get("https://api.scryfall.com/cards/named?exact=" + args['name'])
        response = response.json()
        time.sleep(0.1)  # rate limit
        if 'prices' in response.keys():
            card['price'] = response['prices']['usd']
            return {'card': marshal(card, cardFields)}, 200
        else:
            abort(404)

    def post(self):
        return {
                   'message': "Action disallowed on Scryfall."
               }, 401

    def put(self):
        return {
                   'message': "Action disallowed on Scryfall."
               }, 401

    def delete(self):
        return {
                   'message': "Action disallowed on Scryfall."
               }, 401


api.add_resource(Price, "/")

if __name__ == "__main__":
    app.run(host="0.0.0.0", debug=True)
