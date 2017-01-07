INSERT INTO lawAndSocialDb.user (id, username, firstName, lastName, patronymic, gender, birthdate, avatar, passwordHash)
  SELECT nextval('lawAndSocialDb.user_seq'), 'JohnDoug123', 'John', 'Doug', 'Albert', 'MALE', '08.02.1989', NULL,
    '$s0$e0801$c+pEdHwfgqpU+bfRvfoi2g==$5gMZSzI2Dx2PEjA1dYc3SNQ6e1HdBoHD4HA0NsU9VSI='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'testUser', 'name1', 'surname2', NULL, NULL, '05.07.1990', NULL,
              '$s0$e0801$6zvGkIzar10PijTyyrqQyg==$gizYA/v7z48i5V9D7njSluNTdJP/vsp3pUDav7F/TzU='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'anotherUser', 'Another', 'User', NULL, 'FEMALE', '06.12.2016',
              NULL, '$s0$e0801$5R872TeqCy+HLGtzOolpeA==$L4NTgEEb5Eq0KWeVUaAQyj7co4nivM39ybkS3oWl0hY='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Raarcure', 'Lukzerer', 'Jagmaraced', NULL, NULL, '06.09.1960',
              NULL, '$s0$e0801$aB5IxGlQvqao3G2sPiXcAQ==$bIMrBRxWOu0eoswqkF2htgKPwJXkecS6YAvAQjeN3Uw='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Jagarkes', 'Ramider', 'Crysalmarure', NULL, NULL, '02.11.1949',
              NULL, '$s0$e0801$A1v1bdoG+F2cMhR/w8SdRg==$yjNaOKKipWIYn1ZrRATLjNMOxZSqxo4lrdJmZNQS5ls='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Zedzered', 'Maraird', 'Reimaracarc', NULL, NULL, '09.02.1950',
              NULL, '$s0$e0801$6baLTex/HmhazVZOWWD8Lg==$h+G/8tW3mInDhvingf2/Lnqbgk3tv5pe3CzIOVRjvGQ='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Zedcraes', 'Drakuraktron', 'Creocloure', NULL, NULL,
              '05.02.1946', NULL, '$s0$e0801$WJb6TvYA9mBUPR2J2XxpJQ==$1w6CSBUZMgkbe/LFLcavM1UMOPJCjkkkJWFNaiw8oQc='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Krcresark', 'Cryarkder', 'Mjolarctron', NULL, NULL,
              '25.06.1941', NULL, '$s0$e0801$RMu1/p3Aj3wZMylTb/xecg==$w+usSrVbr1HUvP2jR5M6LhFtyxRWrZ3q+Fq8jrBOWOA='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Zurcraes', 'Krmurarc', 'Marsalmararc', NULL, NULL, '26.10.1944',
              NULL, '$s0$e0801$RX2xDagLP6gQ8v2VkQ8zuA==$rdlWSTuXxSDXZdeJcToW3SOc9sgNak7Vbc7NgkFFg68='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Creocloark', 'Cryirmed', 'Madcresder', NULL, NULL, '01.09.1964',
              NULL, '$s0$e0801$Dxv7B4Xv4bEebbZhvQNEQw==$t7h1QnYCYs2+LPLgYteaULlHE6ofXNzcrJLZRxI/ryE='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Jagmaracer', 'Croarctron', 'Crymaracer', NULL, NULL,
              '30.07.1946', NULL, '$s0$e0801$+rDfLj7vQV9L2RjuTWQy5g==$G+kIjP+gpjERWXp4UhWKvaoDz39D6WAbzEDp/4EvT/4='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Jagarkmur', 'Crysorarc', 'Madmicred', NULL, NULL, '27.03.1960',
              NULL, '$s0$e0801$4Z1eIFg3RJLS/hpXyurmLA==$n+tl5JwoqH2qBkMphMCMWfaXnxHfYdNm2tFj2BW3Lrw='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Mroksortron', 'Zurcrazur', 'Jagmurmed', NULL, NULL,
              '07.07.1977', NULL, '$s0$e0801$NCRKwFYGCYnt4yGUH9VxQQ==$EpEZ14WuBsKx10QIvJR4U3BdEDhEh3tBVG8XIM0bE0c='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Jararcmed', 'Drakmiure', 'Jararkcred', NULL, NULL, '17.03.1943',
              NULL, '$s0$e0801$TjUqSjds6IBNenYGY3F2kQ==$9sh8jr4Y+wtdRi5Fbi6C8EDJxuqsAWFEIJt9wmnnJWQ='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Zedsortron', 'Zurmiriure', 'Zedairark', NULL, NULL,
              '06.04.1972', NULL, '$s0$e0801$HwBAT8k4y/jWsdseABi3qg==$lwrRInNYSp7xYnTh0KeZj4Z890DuxhlJp+oGZMIZY44='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Zorkmeetron', 'Mjolcreszur', 'Cruslamarder', NULL, NULL,
              '19.11.1986', NULL, '$s0$e0801$hSEVYoPwGPStpDDN924x9g==$DIs1Ayy8bj0Bb/EUY2h8zcOkuFK8GSn3cFaZD6aXxDw='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Jagzoirmur', 'Azurzermur', 'Azakird', NULL, NULL, '25.10.1956',
              NULL, '$s0$e0801$yKcrMXb4pO1xnEqXPYmArg==$2eweLHTZkifc8qsknni4eiKHDstBdX9ksJJ00LUpalc='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Mrokmaractron', 'Mormier', 'Madmured', NULL, NULL, '20.10.1958',
              NULL, '$s0$e0801$BSz9ii5D/k6ILJqqK6Cccg==$3vNOlgrol+Dxb1YdZetGsRLPGHPiKsqV9CrOyTijXNM='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Jagcrescred', 'Cazeres', 'Jarsalmararc', NULL, NULL,
              '19.05.1987', NULL, '$s0$e0801$KYghwDDyIj3GzKv1M46pyw==$NPbEf/kSiadTYo7BEXBVGnn43TmO7fe7dOgMyx9XSpk='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Mjolredder', 'Lukmurmed', 'Azurcloed', NULL, NULL, '04.06.1952',
              NULL, '$s0$e0801$G1OnD0D8yzhOED+66KTrNQ==$iPLLaKbT/TzE/MP2zSgqraxM1IAr60vHN7yiysoqyPY='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Azurarccred', 'Ralorider', 'Jagclotron', NULL, NULL,
              '18.11.1962', NULL, '$s0$e0801$BghTRGffqixtQieIz+o9AQ==$2RZOl/PL9X2B/DVFNA07Zodh7QHK9UHFtrwAC5x3u9E='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Mrokurakure', 'Zedzoirure', 'Krmeezur', NULL, NULL,
              '02.03.1980', NULL, '$s0$e0801$1CT5o0H9JW984+SJt6J6gA==$c8ZAMDcawOfatl7mz8CG1yzohAA1NmmQD9P/uf97feI='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Bremaracer', 'Mjolredure', 'Lukmiries', NULL, NULL,
              '25.05.1990', NULL, '$s0$e0801$UZ5r7eNt/oTmVrgdxPvEEA==$qlc9w68rm362WykrTmB+uXXyocYazeeYBWq8uNRYgjo='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Drakarces', 'Zorkmitron', 'Mararked', NULL, NULL, '29.12.1986',
              NULL, '$s0$e0801$IvmoFEAR3ylmgIMmaUKyZA==$C6l433qXNHUwun7TM+ZYqBZPlhZTxqNaieKuPCuA4V4='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Merarkmur', 'Mjolarcark', 'Cruurakure', NULL, NULL,
              '22.09.1944', NULL, '$s0$e0801$bODrZxXMdUrtVAYTaOFMfQ==$z1O/3LrUW41CiLxiq5xsHW16yBm/g0j0MkILcEV23JE='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Camiarc', 'Caloried', 'Zorkmaraczur', NULL, NULL, '24.12.1990',
              NULL, '$s0$e0801$tvJH3OtWs5I4btzkAi1CsA==$S5NGWySoNuGDrzEe2MbrbdzN3lB4o3hycctPs19BRLQ='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Mjolsores', 'Krslamared', 'Madclod', NULL, NULL, '02.01.1978',
              NULL, '$s0$e0801$wrl+sEQ+kxzL0l73j3kCiA==$XM5e8f0fRRUo11dtS7PDQP3sZm6hzqTrRA4q0SlPSys='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Zorkredtron', 'Caarkes', 'Krsalmared', NULL, NULL, '24.02.1951',
              NULL, '$s0$e0801$nDwBle9ThwCYPBjKMt721A==$kDhgdv1dHgpE6XZMP7EJHyAXZo2f5YCw3oDhQAjA9vA='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Croirtron', 'Crocrazur', 'Madlorid', NULL, NULL, '11.07.1966',
              NULL, '$s0$e0801$LGdPTlezlytkosueRo8ZCQ==$CbMCluJ+tQH8ruEhmy1a30ECDAZDstbOUfhwmi+5q00='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Jarredmur', 'Creosalmared', 'Cromaracmed', NULL, NULL,
              '21.02.1957', NULL, '$s0$e0801$XS++iyPAa5NxpRGQEzhjqQ==$d/f9b8MYQ/UNElzlCh4/G/Jjd/HeqGqvXP6+RYvxHrU='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Brecresarc', 'Caarktron', 'Jarmaraczur', NULL, NULL,
              '09.08.1968', NULL, '$s0$e0801$JDg8yi8oZbKtUx6cqi7QuQ==$ErMQk6vi+dtSDUtR3J2wP9IQOGY2V4awNT4OJnkmzzE='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Zorkredark', 'Mjolmirimed', 'Jagloriark', NULL, NULL,
              '23.06.1959', NULL, '$s0$e0801$HmJZxkbS5DCEQQqAhRqF2A==$9Z6/uhh4B5LakUR16OjFkNZvhAmMA3O9ynK5PMqhwxE='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Azakzoirmur', 'Creoaired', 'Creocrazur', NULL, NULL,
              '28.12.1950', NULL, '$s0$e0801$FXmb56iLpnNFnmqTtoHs4g==$XUT+menKh74yJth8Gsk/PdchD+wUc0HCbHuztH6CU2c='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Breairark', 'Zedmurzur', 'Bremimur', NULL, NULL, '22.06.1970',
              NULL, '$s0$e0801$1M5w5y2S9DCntDtNL9Rsiw==$AwvkoEh+TIv5BMWg4ndnNBOwMHQGTibhsDQ1MWL3b/w='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Azurmurer', 'Rayzoirmur', 'Merzoirmed', NULL, NULL,
              '20.01.1958', NULL, '$s0$e0801$Zy4xs6270eAqFMIvaZ7dzQ==$7HGyJKSaKVyiJTNLF99ZFRt8UmsE24doQ3v5DMLfvio='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Azurarcer', 'Croird', 'Bresalmarark', NULL, NULL, '09.06.1987',
              NULL, '$s0$e0801$RShY8J/9mEKS2SMx8yoAHg==$n7Px9C1nLBM5T3BdMjE/pKgbMZHBXc9k4/xO7F8CJsE='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Mjolurakzur', 'Raarkmed', 'Morarkzur', NULL, NULL, '04.11.1971',
              NULL, '$s0$e0801$ZTyO4+n+ckBtCCfipcWcTQ==$N+oQtokmwjpLaIH1PzbJVPcvy6vsyJDV7c1xDarTWr4='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Madirarc', 'Cazoircred', 'Azakzoirtron', NULL, NULL,
              '06.02.1941', NULL, '$s0$e0801$Ttc7SfU6ofL+Vg6FFJT5QQ==$pD/GMIV5dQ25ltYFRd58fLvWQ2QXlE7r5hh5pxa3kBs='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Jarcresark', 'Mrokired', 'Rayzoirarc', NULL, NULL, '08.02.1948',
              NULL, '$s0$e0801$F/4i5CzRX5gV/b7rEIkYpg==$3zMpjfZodZf+tuUFu3eEGyvQv3cQYWkX53NWO9r63NU='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Marirer', 'Zurmeed', 'Rayarczur', NULL, NULL, '23.01.1980',
              NULL, '$s0$e0801$uTwe2jZgsxIffB2+5Kfh4A==$S4LOxf+vRpZPiP3t8Znd7SxQqkXqljFZriT45qo/mdA='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Mersalmared', 'Marmeemed', 'Crocrader', NULL, NULL,
              '09.04.1967', NULL, '$s0$e0801$PiCB6zJZmbbjRjOmhhRZ4w==$fnzoXs8j3DaGyZT/nHyHGKbwIJ4jLDkxlFA5Wsfnq0Q='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Zorkmurtron', 'Crucresure', 'Creoairer', NULL, NULL,
              '27.07.1957', NULL, '$s0$e0801$E75oGIq3NIwtXR0T0TdkmA==$nF58FAnO4DyymoPQ4ulk80u7TTJQEEfktxyKJr3OYi4='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Marairark', 'Crymiritron', 'Madzoirarc', NULL, NULL,
              '29.09.1945', NULL, '$s0$e0801$0i6/Rs45Xj1wUF5vEh0NIQ==$74+7QIpoe5BJ76tPkkmXleeFv+E+RYEgSi/Of0nm1yw='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Zedzerd', 'Cacraarc', 'Zurcraes', NULL, NULL, '26.11.1955',
              NULL, '$s0$e0801$eAZZJiHcnvQhDX8VeuGTMQ==$Wo1BVESxl4MUWj9VrS4Zt3cisAtYctqw/EbKZc4sGnE='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Careder', 'Zurclod', 'Krcloark', NULL, NULL, '11.11.1951', NULL,
              '$s0$e0801$5eK8IomvafuatGnKSNgM1Q==$9aM54Q1mfrW0C6FOCoyXDZWFh5z4kl/IXDBhOo2ZAtI='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Reimimur', 'Mrokmaracer', 'Morsorcred', NULL, NULL,
              '30.05.1947', NULL, '$s0$e0801$Q5RIo+vV9XoZr/Ogc0nM7w==$HP81f5ueFoZ6r+nAQm2EeohcgjcbeSt/4waWWUr95NY='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Croslamararc', 'Rameezur', 'Madsormed', NULL, NULL,
              '08.05.1946', NULL, '$s0$e0801$koDxPNW6xoYLJ+J9Hpg/UQ==$Wn28bNt3GHhrB1H4gzT2Yq7xxfD1AiZiDvdMReIexTk='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Drakreded', 'Ramirimed', 'Azurires', NULL, NULL, '08.05.1969',
              NULL, '$s0$e0801$T/OaCV3XmvnYMa6DQimZDg==$IfPTjSBcg19W6cS2b4flz+f8hyrJxtlakUxTGvU12CA='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Morairark', 'Rayzered', 'Cryarcarc', NULL, NULL, '09.06.1981',
              NULL, '$s0$e0801$tBoymBDtKD4UQsZceX6LGw==$wRDnSBuV/OP+eILu07JUsnlRHwindVex7u1UHyqyGGs='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Crocloark', 'Mormurd', 'Azakirer', NULL, NULL, '09.09.1986',
              NULL, '$s0$e0801$2OO3MEHYHj7KMUKAWoBRiQ==$WaXgDxo3RqVMQFTjCXgdznO78dgV2PaOCZlDrltXOBw='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Morirtron', 'Bremeearc', 'Jarredure', NULL, NULL, '29.05.1977',
              NULL, '$s0$e0801$jKM0ZzvPqyGIgPShms2PRw==$b9ByTwxghgVTObjQFLXCGrSBXT9wbVEtV0Ybzhwgli0='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Ramurark', 'Ramaraczur', 'Morsalmared', NULL, NULL,
              '30.03.1960', NULL, '$s0$e0801$ZUIyrSSFgvSrfJMsgaSkNw==$1qwPpr44QAj1g2yDVw6STbFRFv4cDunRBxxXPg1575Y='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Mermier', 'Morzercred', 'Azakarkzur', NULL, NULL, '09.10.1953',
              NULL, '$s0$e0801$Tkx5Ue+iZF5E7DZVYv7VHA==$TCYsskEHF7dw0IYKesZGgFbPhugxUjCahJk1HUqGEa4='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Zorkuraked', 'Breaires', 'Cryairarc', NULL, NULL, '28.10.1967',
              NULL, '$s0$e0801$NOdCsupgnvME/RvM9iuvig==$BQ6UNMvHwnxtJLMerUjzi84+RNXNCeExXQJGQGfe55Y='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Creomurer', 'Cryirzur', 'Krlorier', NULL, NULL, '23.03.1986',
              NULL, '$s0$e0801$RU41dcHhOf26ToHTh2WJuw==$8foD+ByDjYYIEObgaj6J8JFxmlR6eorfG/xDYRR5xz0='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Lukurakder', 'Drakslamarmed', 'Azakarced', NULL, NULL,
              '17.05.1940', NULL, '$s0$e0801$Ah3LPqFdn4YcGtOqLGTOng==$WlQGIjx5YOmJ56NCusewFDNj4L7yfvO04H5Sb1OpgpA='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Azakclozur', 'Marmiried', 'Azurmaraces', NULL, NULL,
              '20.01.1953', NULL, '$s0$e0801$Mi4zl5+WBS2heQmfPVEA1A==$gU63bXyHNa8Gh8wweW/VueAPy2paeUiVW4jofJT6nPM='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Cromier', 'Bresalmarark', 'Cromiark', NULL, NULL, '17.08.1948',
              NULL, '$s0$e0801$RuPadLV1J0BEyUFtEEGxcg==$WWxowYXk0lUbZ3cCscBNpXu5QRe+D6rGOBB9ni3F6xk='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Morlorizur', 'Lukmider', 'Mormimed', NULL, NULL, '20.10.1979',
              NULL, '$s0$e0801$EZchiEbgKl7uffPpjEjmgA==$XCZ5oFc3iK82JMgEiGWv/wmrdvFN44wGe+XMLQe/lwQ='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Crusalmarmur', 'Zurairder', 'Azakarkmur', NULL, NULL,
              '13.04.1959', NULL, '$s0$e0801$6xPjuiQFYBMEp+n8h5e17w==$uWUx/y7EPpw6Hx9PdJtFlkowgIJBBcdHrCHCDgqJgjM='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Zursalmararc', 'Mrokarctron', 'Morzoirzur', NULL, NULL,
              '17.09.1971', NULL, '$s0$e0801$ndBhFB10wo1Iaxt3cCqqvQ==$bdjtzVaH6Vtjw1O6uIOWkyQveCidmTbyCJekqj5THSY='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Cacrader', 'Crumaracure', 'Merzerd', NULL, NULL, '25.05.1968',
              NULL, '$s0$e0801$sJmkIrCutLsRcbCr2oNVjw==$ysZGlIGMg2zLBVtfzNIPd1EQzV47RVDt/ZHjZkIslqw='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Raycraes', 'Jarslamarure', 'Marmiries', NULL, NULL,
              '16.11.1970', NULL, '$s0$e0801$2hxfON/cvxlh1qmxd4MGGA==$8TxVFYEPBBJEjhRouJUOkzmpoa0Lkn2W0Xn1PYsFIGM='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Ralorimed', 'Merredure', 'Casorzur', NULL, NULL, '27.06.1967',
              NULL, '$s0$e0801$ySPmn38Z6wFOl97YRF/T4g==$inZ/+EbxtNEzgYeBMZ0rruwGULHArdHk+ZibLAv9I6U='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Ralorizur', 'Mrokird', 'Lukmaracer', NULL, NULL, '26.10.1946',
              NULL, '$s0$e0801$txX3L4N/Uk2o/BhRfl86CQ==$8Mv4The/5lbR2km91kMg0e7VrcS6EiD3tKAH76cc6/E='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Raairarc', 'Creomiried', 'Cazoirmur', NULL, NULL, '04.07.1965',
              NULL, '$s0$e0801$6AW9f0+ZT5Ksaz6XtGBVtQ==$hoTa2v1taJZPnyuhIv9/SbvFuUe9C7KXtErxVX2POKE='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Lukredure', 'Caslamararc', 'Zedmeemur', NULL, NULL,
              '16.10.1959', NULL, '$s0$e0801$rQiX1DU4Frjp3C0FmoY5KQ==$tp9MD2FQGP3fZNt8cG27pzqIpEBeESKDZ2OQzMCmIPE='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Cacreses', 'Ramaracder', 'Zursalmared', NULL, NULL,
              '14.01.1963', NULL, '$s0$e0801$Lwz7rUFqIAQPwh0qrVRvQQ==$9Rpr1q46Mey+BAME3I0Mj/b0z+JoyquuSWg9Qw8NSdw='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Draksalmared', 'Azurzoired', 'Caaircred', NULL, NULL,
              '08.04.1990', NULL, '$s0$e0801$8qMx2Vss5WYAutkMBZzJLQ==$7PWrVEJL9pQGz1Js0MOPtE6D3mjZNKAb5C1B0Iu4gK4='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Azurmaracder', 'Croslamarmed', 'Cromaraczur', NULL, NULL,
              '23.10.1984', NULL, '$s0$e0801$aGq7nYwg/oqP8N+GUAqriA==$W/+M6tKVQwM9/kUO8+dAx65rRLHircg6Ih8zKnQpVGc='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Reiarkd', 'Jarurakd', 'Madslamarder', NULL, NULL, '19.08.1990',
              NULL, '$s0$e0801$tVdWQQsWx61AS8/jy0BCkA==$XMz44uo/1fNjNwuDwRnicXnSlTwD7AOypOw+ICXph/s='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Camurark', 'Morzerure', 'Krzoired', NULL, NULL, '05.01.1953',
              NULL, '$s0$e0801$8Ic+xy9Jjlaa3jf7p8G4fQ==$Fe5+n0c0v4+EPMDPHvTTdu0IqRu1+4/00GzpRuCsEIw='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Reislamarer', 'Creomeeed', 'Mermaraczur', NULL, NULL,
              '27.06.1990', NULL, '$s0$e0801$NL8TvBEmmePSCs8C5g98Aw==$r4td35hdNUNAtyK7GE7RYocxTNHVfTqIUcO6Z4+iQmw='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Madcraure', 'Raymeeark', 'Azakmeearc', NULL, NULL, '08.11.1957',
              NULL, '$s0$e0801$R9DKLG/2s/LuFuTbkBaj7Q==$RBCtvfLXJp00ZNFyZ2HJJnGBvJaWb0olEk+UdbBAEBQ='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Crymurd', 'Cryzoircred', 'Mormies', NULL, NULL, '22.11.1975',
              NULL, '$s0$e0801$PPAKXhAn9hUPQ4roSwzYIw==$pjAdeQJ2dUF6VNbwAdDvL3IgsdynZM9aMxTF24Pf5h4='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Drakslamarder', 'Azurairark', 'Krmiriure', NULL, NULL,
              '13.05.1988', NULL, '$s0$e0801$mpMHnAwIk6S0hIl5fNLgFg==$wzddrE1IUCj9vdRk+e+5W6zDgxo8mtS316dO3KtfsLk='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Krmiritron', 'Zursorder', 'Zurzoirtron', NULL, NULL,
              '22.03.1969', NULL, '$s0$e0801$H5SZpp9a4jYkiVXLn+rU5g==$MW7UUBPogWSr9F8/pjDszIebgQ0X9rWhhF5A0UzlG1k='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Bremaracure', 'Mrokcrader', 'Zurmurder', NULL, NULL,
              '15.08.1983', NULL, '$s0$e0801$OP3Dm9DZYc2y1B7uQPYSXw==$EgFc6lR/9ILWXk854Rw9meF//G902VT4C90PIkm4B1A='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Brezertron', 'Mormied', 'Reisalmarmur', NULL, NULL,
              '10.07.1965', NULL, '$s0$e0801$lazUZC4Hd+E5qr4Lgn5+TQ==$cJSE0xbP7Or8Du+WUZLGrgQthbFt/e/Wg2dNELpURu0='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Mjolcraarc', 'Mrokmurarc', 'Jararctron', NULL, NULL,
              '25.03.1982', NULL, '$s0$e0801$U7H4pug95EQucHXOH/ZVLw==$NXp2OJ/zYsbq6gvKnmjhgeAqLzNnayQEMJhXQttQQQ8='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Azakmiritron', 'Cruirzur', 'Azurmurer', NULL, NULL,
              '26.10.1988', NULL, '$s0$e0801$dIar+vRixShMs3IWC5f8aw==$qMsDVcLOuWERCikbyttxtwNScoSGNZHKiOABaJWyWO8='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Raymiriure', 'Creoarkmed', 'Breredmur', NULL, NULL,
              '23.05.1959', NULL, '$s0$e0801$/uxwn9wivd7WcOJFD/lMzg==$ir8/R/OP2FstBhbKUf8YNX0uErWfCQ+3ylMswHKL9lE='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Crucraed', 'Brecloark', 'Racraark', NULL, NULL, '01.03.1968',
              NULL, '$s0$e0801$NOQ2prVmZR9QkkIqhVfKVA==$zRXCEb1w+eZ959W7wsa0UfBidstFYmcjbfQTZOZJNY0='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Luksalmares', 'Morloritron', 'Mermurd', NULL, NULL,
              '14.08.1952', NULL, '$s0$e0801$nzFLE523MhAlO0PP2OX83g==$iJ1oMVlXygJ8QEY4LfISdwThXZoCsniuJdod5ObKQQw='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Zedmurer', 'Jarirark', 'Raylories', NULL, NULL, '31.12.1947',
              NULL, '$s0$e0801$3DHhbPEWrdkgptsWyvrb2A==$PZM5R6UNZ4GIVacQ/n2rywIaBLF9iFmrzC8gMlqB9oc='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Zedclomur', 'Azakmeeed', 'Drakarker', NULL, NULL, '30.11.1973',
              NULL, '$s0$e0801$YX3WtRCqmXHzd9gUC3/QfA==$0PHvIR4cJf9GVBW/N4Z+cim40CW/nRBxhQKfDoeKBno='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Raairzur', 'Reimurark', 'Zedmied', NULL, NULL, '04.12.1974',
              NULL, '$s0$e0801$F6+aCvpiDUwS99tDTdd74A==$2iFnj353M6SQf9+el0w6O16cNsXuS5wKYv6o7lfYBAI='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Ramurtron', 'Zorksord', 'Creomirimed', NULL, NULL, '05.02.1966',
              NULL, '$s0$e0801$2rvNG8DE2np1scqXXM5Vjg==$+relsSx87E0oZyErqJRhLkJG7lUpXJii+FT8bDCo1p8='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Morairzur', 'Zurloritron', 'Casorer', NULL, NULL, '13.12.1973',
              NULL, '$s0$e0801$UYBgOL904Y1d7RbzP5yGtw==$3MXWwA8+5wTOCSAgsvc4RfUPvNeZFvLrONq1zncZyw8='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Lukmaractron', 'Mrokredarc', 'Marcresure', NULL, NULL,
              '12.12.1980', NULL, '$s0$e0801$HOPTG0F4vUsWYMIyki1tpw==$XlOIZAsWNkEIYbX2wqNvM/TogJfQFrSPMjXn1bwWnPw='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Reimied', 'Zorksorder', 'Zorkairzur', NULL, NULL, '17.12.1981',
              NULL, '$s0$e0801$IcQnynhvVGNT/AQev1QuzA==$CHNhA0I+Bt74ZI4HcsL7sbnKrpD3qxlalbV3Mqq3CMM='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Brearkmed', 'Rayarkes', 'Madzoirmur', NULL, NULL, '10.11.1989',
              NULL, '$s0$e0801$i4x4lO2FiBT69Y+MVzVauA==$khRaiTI7Hhwo0hLtHxddtHRRcHW01MjS1jzJanf2R80='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Zedredder', 'Zorkarkd', 'Mormiure', NULL, NULL, '19.05.1989',
              NULL, '$s0$e0801$YCME0bFKsa4B3WeUkX6+bg==$mXAAcqVdzfLqfgNqX/6TOaQBCp8D0H4Lx5T2ywA/DPo='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Reimitron', 'Azakmirier', 'Jarcresd', NULL, NULL, '24.11.1986',
              NULL, '$s0$e0801$Jn2Ne0vppgAThkOlEDqHdQ==$R8tkeOv17xi0Yy4GNyFPr9ektCIRphP3KMYC8u4zcNo='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Reicresed', 'Creomurzur', 'Mercrad', NULL, NULL, '17.10.1973',
              NULL, '$s0$e0801$ljFxhlDT4oJ0IffooBsvFQ==$5CugtIovwqyIwktckmYx/UrkwcO1qztyHaQjgJKtz9o='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Cacloed', 'Zorkmiriarc', 'Mjolcresmur', NULL, NULL,
              '27.04.1974', NULL, '$s0$e0801$XOL7FSO+ikWl90H1iNe4iw==$9Syvw7O6ya6EaCleeQetdwkOZGSULbDEGkQG0TTaBys='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Reiclotron', 'Drakmiritron', 'Merarkmur', NULL, NULL,
              '06.10.1952', NULL, '$s0$e0801$0WwBZx+XYZjENnWhkWUFPw==$dQ3cES7QEf+hnqsdKTtwHoQeZc+syZLPe5Sawv+gDBM='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Mermaracure', 'Jagzoird', 'Creosalmarmur', NULL, NULL,
              '31.01.1959', NULL, '$s0$e0801$kx4jincTSUQ5ZtNiNQ2rxw==$oWSVaXMKL7C3ys1PIHQbSjTgyOxcLExEAwevP3byF2I='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Azakzermed', 'Zururakarc', 'Crocraer', NULL, NULL, '14.01.1944',
              NULL, '$s0$e0801$HRuvkMUIWthotMMtDYQecw==$w9hMgUB/eRsO16KoeF74yIfi/FoLy5yhJHnHfdLgMpU='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Zedcratron', 'Crysord', 'Mararccred', NULL, NULL, '14.02.1940',
              NULL, '$s0$e0801$mketi++tTScJhM101FHE5Q==$Y/UYe1BDLWAbaDcOP2cXWaVj0YPXMKBSotA4ZptHhQ0='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Zurmitron', 'Jagsorder', 'Krcrader', NULL, NULL, '06.12.1987',
              NULL, '$s0$e0801$aetyngN8Y7OhauF69z6MEw==$ZoHb1MIkgLwGbIPEj46NWrX4oKSfwWlxzuKy0+WY5Fs='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Zurzerzur', 'Jarcresark', 'Zorkarcer', NULL, NULL, '22.11.1948',
              NULL, '$s0$e0801$T6nopLnqMrY9xJh3Wfm/Ag==$9MPFBTQTuHLXlZIcqHi7zXdsykut0DXAHdMnB3bOeKc='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'Azaklorid', 'Rayloriure', 'Azaksored', NULL, NULL, '06.01.1941',
              NULL, '$s0$e0801$a7EyedoabZlXLxC7cS0c1g==$ifwYwSVhvedB/VRTjYwuM97VdC+MO/nN6VngNvf88Sg=';

INSERT INTO lawAndSocialDb.school (id, user_id, name, country, city, yearsFrom, yearsTo)
  SELECT nextval('lawAndSocialDb.school_seq'), 2, 'School1', 'Russia', 'Moscow', 1985, 1990
  UNION ALL SELECT nextval('lawAndSocialDb.school_seq'), 2, 'School2', 'Ukraine', 'Kiev', 1995, 2000
  UNION ALL SELECT nextval('lawAndSocialDb.school_seq'), 1, 'School3', 'Russia', 'S.Petersburg', 1990, 2000;

INSERT INTO lawAndSocialDb.university (id, user_id, name, country, city, yearsFrom, yearsTo)
  SELECT nextval('lawAndSocialDb.university_seq'), 2, 'Harvard', 'UK', 'London', 1985, 1990
  UNION ALL SELECT nextval('lawAndSocialDb.university_seq'), 2, 'SPBGU', 'Russia', 'St.Petersburg', 1995, 2000
  UNION ALL SELECT nextval('lawAndSocialDb.university_seq'), 1, 'Univ3', 'Ukraine', 'Kiev', 1988, 1994;

INSERT INTO lawAndSocialDb.contacts (id, user_id, email, phone, website)
  SELECT nextval('lawAndSocialDb.contacts_seq'), 1, 'testMail@gmail.com', '+7999111222', 'johndoug.com.ua'
  UNION ALL SELECT nextval('lawAndSocialDb.contacts_seq'), 2, 'test@mail.com', '+7226663331', 'tesuserwebsite.com';
/*
INSERT INTO lawAndSocialDb.user_school (user_id, school_id, yearsFrom, yearsTo)
  SELECT '4', '1', '1994', '2000'
  UNION ALL SELECT '4', '3', '1955', '1960'
  UNION ALL SELECT '1', '2', '1984', '1940';
*/

