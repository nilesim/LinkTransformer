# SelinKaya
A Web Service to convert URLs to deeplinks and deeplinks to URLs.

There are two endpoints for this transition.

1. web2deep
2. deep2web

For project setup, use postgres initilize script provided. 

* cd \LinkTransformer\docker-entrypoint-initdb.d\
* docker network create trendyol
* docker network ls
* docker run --name trendyol-db --network trendyol -p 5432:5432   -e POSTGRES_PASSWORD=Passw0rd -d postgres

done creating the db, next steps are not needed but incase if you want to check anything, you can use postbird or jump into db console by:
* docker exec -it trendyol-db bash

psql -U postgres -W

... insert password (Passw0rd)

to clean the docker parts after all is well(to shut down database and clean what is left):

* docker rm -f trendyol-db
* docker network rm trendyol

(for more info you can check:
https://medium.com/@selinkaya.ce/docker-cheatsheet-c3cc7cf83a5c
)

<h1>Endpoints</h1>
<h2>web2deep</h2>
<table style="border: none;">
<tbody>
<tr>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.2; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">request</span></p>
</td>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.2; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">response</span></p>
</td>
</tr>
<tr>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&amp;merchantId=105064</span></p>
</td>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">ty://?Page=Product&amp;ContentId=1925865&amp;CampaignId=439892&amp;MerchantId=105064</span></p>
</td>
</tr>
<tr>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">https://www.trendyol.com/casio/erkek-kol-saati-p-1925865</span></p>
</td>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">ty://?Page=Product&amp;ContentId=1925865</span></p>
</td>
</tr>
<tr>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">https://www.trendyol.com/casio/erkek-kol-saati-p-1925865?boutiqueId=439892</span></p>
</td>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">ty://?Page=Product&amp;ContentId=1925865&amp;CampaignId=439892</span></p>
</td>
</tr>
<tr>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">https://www.trendyol.com/casio/erkek-kol-saati-p-1925865?merchantId=105064</span></p>
</td>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">ty://?Page=Product&amp;ContentId=1925865&amp;MerchantId=105064</span></p>
</td>
</tr>
<tr>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">https://www.trendyol.com/tum--urunler?q=elbise</span></p>
</td>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">ty://?Page=Search&amp;Query=elbise</span></p>
</td>
</tr>
<tr>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">https://www.trendyol.com/tum--urunler?q=%C3%BCt%C3%BC</span></p>
</td>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">ty://?Page=Search&amp;Query=%C3%BCt%C3%BC</span></p>
</td>
</tr>
<tr>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">https://www.trendyol.com/Hesabim/Favoriler</span></p>
</td>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">ty://?Page=Home</span></p>
</td>
</tr>
<tr>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">https://www.trendyol.com/Hesabim/#/Siparislerim</span></p>
</td>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">ty://?Page=Home</span></p>
</td>
</tr>
</tbody>
</table>
<h2>Deep2web</h2>
<table style="border: none;">
<tbody>
<tr>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.2; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">request</span></p>
</td>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.2; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">response</span></p>
</td>
</tr>
<tr>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">ty://?Page=Product&amp;ContentId=1925865&amp;CampaignId=439892&amp;MerchantId=105064</span></p>
</td>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892&amp;merchantId=105064</span></p>
</td>
</tr>
<tr>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">ty://?Page=Product&amp;ContentId=1925865</span></p>
</td>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">https://www.trendyol.com/brand/name-p-1925865</span></p>
</td>
</tr>
<tr>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">ty://?Page=Product&amp;ContentId=1925865&amp;CampaignId=439892</span></p>
</td>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892</span></p>
</td>
</tr>
<tr>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">ty://?Page=Product&amp;ContentId=1925865&amp;MerchantId=105064</span></p>
</td>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">https://www.trendyol.com/brand/namei-p-1925865?merchantId=105064</span></p>
</td>
</tr>
<tr>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">ty://?Page=Search&amp;Query=elbise</span></p>
</td>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">https://www.trendyol.com/tum--urunler?q=elbise</span></p>
</td>
</tr>
<tr>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">ty://?Page=Search&amp;Query=%C3%BCt%C3%BC</span></p>
</td>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">https://www.trendyol.com/tum--urunler?q=%C3%BCt%C3%BC</span></p>
</td>
</tr>
<tr>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">ty://?Page=Favorites</span></p>
</td>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">https://www.trendyol.com</span></p>
</td>
</tr>
<tr>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">ty://?Page=Orders</span></p>
</td>
<td style="padding: 5pt 5pt 5pt 5pt; border: solid #000000 1pt;">
<p style="line-height: 1.38; margin-top: 0pt; margin-bottom: 0pt;"><span style="text-decoration: none;">https://www.trendyol.com</span></p>
</td>
</tr>
</tbody>
</table>