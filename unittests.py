import unittest
import json
import requests
import os

dirname = os.path.split(os.path.realpath(__file__))
print(dirname[0])
with open(os.path.join(dirname[0], 'config.json'), 'r') as f:
    config = json.load(f)
    deck_db = config['card_db']
    deck_arg = config['deck_call']
    pricer = config['card_pricer']
    pricer_arg = config['pricer_call']


class MyTestCase(unittest.TestCase):
    def test_collection_get(self):
        self.assertEqual(200, requests.get(deck_db + deck_arg).status_code)
        # didn't end up using the individual card REST api, ignored for tests

    def test_collection_put(self):
        r = requests.put(pricer)
        self.assertEqual(401, r.status_code)

    def test_collection_post(self):
        r = requests.post(pricer)
        self.assertEqual(401, r.status_code)

    def test_collection_delete(self):
        r = requests.delete(pricer)
        self.assertEqual(401, r.status_code)

    def test_scryfall_get_pass(self):
        payload = {'name': 'Dark Ritual'}
        r = requests.get(pricer, params=payload)
        self.assertEqual(200, r.status_code)

    def test_scryfall_get_fail(self):
        payload = {'name': 'not a real name'}
        r = requests.get(pricer, params=payload)
        self.assertEqual(404, r.status_code)

    def test_scryfall_put(self):
        r = requests.put(pricer)
        self.assertEqual(401, r.status_code)

    def test_scryfall_post(self):
        r = requests.post(pricer)
        self.assertEqual(401, r.status_code)

    def test_scryfall_delete(self):
        r = requests.delete(pricer)
        self.assertEqual(401, r.status_code)


if __name__ == '__main__':
    unittest.main()
