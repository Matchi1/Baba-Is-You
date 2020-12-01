import os
import requests
import shutil
from bs4 import BeautifulSoup


# Need to run this script
# pip install requests
# pip install bs4
# pip install wget

def download(file_location, image_name, image_url):
    resp = requests.get(image_url, stream=True)
    local_file = open(file_location + image_name, 'wb')
    resp.raw.decode_content = True
    shutil.copyfileobj(resp.raw, local_file)
    del resp


url = "https://babaiswiki.fandom.com/wiki/Category:Properties"

response = requests.get(url)

if response.ok:
    soup = BeautifulSoup(response.text, "html.parser")
    table = soup.find('table', {'class': 'article-table'})
    lines = table.findAll('tr')
    number = 0
    for line in lines:
        cases = line.findAll('td')
        if len(cases):
            case = (cases[0].find('img'))['data-image-key'].split("_")[1]

            if not os.path.exists(os.getcwd() + '/' + case):
                os.mkdir(case)

            try:
                download((os.getcwd() + '/' + case + '/'), "Prop_" + case + ".gif", (cases[0].find('img'))['data-src'])
            except KeyError:
                download((os.getcwd() + '/' + case + '/'), "Prop_" + case + ".gif", (cases[0].find('img'))['src'])
            finally:
                number += 1
                print(f"{number}/{len(lines) - 1}\t -> Downloaded\t {case}\t\tGIF IMAGE -> PROP")
