INSERT INTO lawAndSocialDb.user (id, uuid, username, firstName, lastName, patronymic, gender, birthdate, avatar, passwordHash)
  SELECT nextval('lawAndSocialDb.user_seq'), '14675655-63bb-4dad-99c2-005768902ade', 'JohnDoug123', 'John', 'Doug',
    'Albert', 'MALE', '08.02.1989', NULL,
    '$s0$e0801$c+pEdHwfgqpU+bfRvfoi2g==$5gMZSzI2Dx2PEjA1dYc3SNQ6e1HdBoHD4HA0NsU9VSI='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '337daaed-b5c7-4fb8-bb49-b96816a89699', 'testUser', 'name1',
              'surname2', NULL, NULL, '05.07.1990', NULL,
              '$s0$e0801$6zvGkIzar10PijTyyrqQyg==$gizYA/v7z48i5V9D7njSluNTdJP/vsp3pUDav7F/TzU='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'b2ddc07f-8e5c-40b5-ac06-dc124cab7cc6', 'anotherUser', 'Another',
              'User', NULL, 'FEMALE', '06.12.2016', NULL,
              '$s0$e0801$5R872TeqCy+HLGtzOolpeA==$L4NTgEEb5Eq0KWeVUaAQyj7co4nivM39ybkS3oWl0hY='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'd668cf7b-1b8e-4772-b52e-61b3ad725757', 'Azakairer',
              'Breredtron', 'Cazermed', NULL, NULL, '17.01.1978', NULL,
              '$s0$e0801$CUwiHQD1r8tdzFr3Z5Ic6w==$/WudQFKuBdG7eoFVleKPFJhN/Uf+7NNJ9143P7LRGyQ='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '39da2c85-318d-4616-acc0-0b930cdc4191', 'Jagairtron',
              'Cromiriark', 'Marcrazur', NULL, NULL, '19.07.1958', NULL,
              '$s0$e0801$LsCQnjPIVaIqVX8J83bI9g==$56bH/880fUBfLPQPYQ0G7/TbJpDfkbEAUYAhkn0/bog='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'a1486fd0-b2a0-4847-8ba7-44920b268588', 'Cazerure',
              'Reisalmares', 'Mrokcloure', NULL, NULL, '28.12.1964', NULL,
              '$s0$e0801$mXGWJ0xe79BPRBatJhwuOQ==$FP77rK1YKUDVGut4hb7MqiKQpp4YFHA7rxqbxhKcV7s='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '49726f34-fa51-43a6-a59f-8fe6e990c277', 'Lukzerd', 'Cairmed',
              'Zorkmurmed', NULL, NULL, '06.11.1971', NULL,
              '$s0$e0801$ogYfhmvDugkjWSoJ76xImA==$KIPg+3TCqfcJFetFLJPjfMYfhlvkpkX3+XE8QBawIuU='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'cbfa972e-c3bc-4017-9ae2-912f72c4c382', 'Jagirark', 'Merarkcred',
              'Madairark', NULL, NULL, '01.01.1967', NULL,
              '$s0$e0801$D2kWH9xGBvzDjKLujZWDRA==$bPkoQL7zja+mAwNMJk5vfjayY7XMiUnOYGAzeMqt+Rs='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '12232b56-d9dd-47e9-9770-b0c945c06531', 'Zedsalmarark',
              'Careder', 'Cryarced', NULL, NULL, '18.02.1950', NULL,
              '$s0$e0801$QrxwZ5k9LqfaESfrtu/tzA==$6rDtP4rFEvrQQXJxtIqC2pwYiTXazWg74/1sUu6J3dA='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '156ec14f-642f-417e-86a9-e1f52a67e266', 'Cruredmur', 'Lukires',
              'Creomiark', NULL, NULL, '08.10.1968', NULL,
              '$s0$e0801$G8EaKuFaYf/ywR5qnCOGxw==$h8p4LSUybT7GBPUgHmMhaePbMT+bxCrMSgnLeGyk98k='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '619ff222-aef9-4c83-9f78-b3799ca9708c', 'Croclocred',
              'Zururakarc', 'Drakcrader', NULL, NULL, '28.05.1990', NULL,
              '$s0$e0801$EVW4oVVq0SqRX+CIOzMQDQ==$5YZlw4rjZHycntFvDuP8zCqhpxr9iFUpNWlU3zLzwkM='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'ab4b75a1-ff8a-47d8-92b9-3a39adeca772', 'Breirmur', 'Caredcred',
              'Azurmirid', NULL, NULL, '12.05.1963', NULL,
              '$s0$e0801$gcIE48WU/LpHnyc/LW3TfA==$DZeE95V9LKo7uq/9oQjnqtJ1iNe6899pVt1KC5GKeaI='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '2aa490c5-3ea6-4c7b-a417-13f7ade2ef41', 'Jarzoircred',
              'Creoarcarc', 'Mjolmaracder', NULL, NULL, '26.05.1942', NULL,
              '$s0$e0801$p1FNOinLkLZEj4ZJOIPgGA==$XuNcxuqiCUtFlT9maRFUB2v+6KgzeNk7/LZ7rRVq1UE='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '9b2f91d7-9651-444f-b9af-e5d7cb844a3d', 'Azaksorark',
              'Moruraker', 'Ramier', NULL, NULL, '03.07.1957', NULL,
              '$s0$e0801$2u8qkNNe34wLO47MAogqhA==$8Dcp7Lt6VhKJFvBWmdZE5ZpznzZN++0VKIPEwi4WtaE='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'e095a84f-80f1-4189-ac10-55c8d74daf40', 'Madsorcred',
              'Raysormur', 'Lukcresd', NULL, NULL, '30.06.1973', NULL,
              '$s0$e0801$abtescIWasf4G4VDVT1kjw==$RIddj+e74vnxX3yNzAxfgLW0zamo9Mcgi7Vcg3IJT4Q='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '48addbb7-b21b-4890-b408-185a783ccc66', 'Ramied', 'Rayzoirmur',
              'Zorkcresmed', NULL, NULL, '01.10.1967', NULL,
              '$s0$e0801$XlMdmeIMWvka5WDgsSFIQg==$580N8j/HHoLwslB99LMdhIsWqjbNwYSEDfCoBOEeI8k='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'b2f617df-6dca-4d95-b860-497d1bcba532', 'Ramies', 'Ramiriark',
              'Azurmeetron', NULL, NULL, '16.04.1961', NULL,
              '$s0$e0801$DAtZ1sW4d5AM+gHdYj+a4w==$oFn4WxBFtgVJKDfgAh7QjEEpOWUztNnw01fVOn77OCw='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '70fb4ed0-5236-4f2b-80c4-7fcde4d026b5', 'Marslamared',
              'Lukmimur', 'Krcresure', NULL, NULL, '15.01.1954', NULL,
              '$s0$e0801$iAMafXDo4nEHgAo8/djk9Q==$h2XxxOiBrn4XYp/P0g1r2loZFKoyU8ZUzggnq1eAm+c='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '11b9d0c0-c585-432f-9c01-c811e4d2a789', 'Krcloes', 'Mormirimur',
              'Jagirder', NULL, NULL, '10.08.1950', NULL,
              '$s0$e0801$wfACvzo0vsd6ETA9AefAtA==$VBvRCYii58OJQC4oUrTVAVkPQdJz41RGeKTjn5WDPPk='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '169b745a-a1be-4e1d-b4bc-9cc8369baddc', 'Croircred',
              'Azakairark', 'Mermitron', NULL, NULL, '02.02.1977', NULL,
              '$s0$e0801$62ip2RwyyJR4jHZ+AyFmrQ==$99T2FkllSiGoUpIbQGPz2Rqc3+OfNRBYIWtorBYkY5Q='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '338d2268-fc87-4eda-aeb1-6bd94855f579', 'Jarcrad', 'Brecraark',
              'Marzoirtron', NULL, NULL, '26.02.1984', NULL,
              '$s0$e0801$Xzj61S9LqNoqZp4s+ZWfRg==$HYv8P/sJHSujbrpoWMDnleOiLqK4aB8TJLa1E73izUI='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '299c4acd-b796-445f-b633-f94fb7cd90bd', 'Jarsalmarder',
              'Crylorizur', 'Merzoirtron', NULL, NULL, '09.11.1976', NULL,
              '$s0$e0801$L2NPsIeQNE/deuRY45r6cQ==$cK+VzoXlgDaXdoF6tQ3UW6StQoTc4WQbzClRZi1T1t4='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'd3b84484-408f-4f4f-85a4-593837618672', 'Azakmirimed',
              'Bresorzur', 'Jarsorarc', NULL, NULL, '05.04.1982', NULL,
              '$s0$e0801$7h8t4B0q3Y1xhl6Jduga/Q==$syYF56ncBFX6YpSvyrJO+HRdSlXozc/oA78I1LF/Fx0='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '6a14cc38-ad86-4e69-ba8e-36ab7c68553f', 'Maduraker',
              'Reisalmartron', 'Cryzoircred', NULL, NULL, '09.07.1970', NULL,
              '$s0$e0801$tIXwnuM/LGQ7W6980u8eiw==$O9m6RoZKfY7xW1nmPE9ARR5qMj4tRH5yYmimpwgKtlg='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '0650181e-0867-4003-b08f-a0767ef1b38e', 'Ramurtron', 'Zurmeearc',
              'Mjolmeemed', NULL, NULL, '04.12.1979', NULL,
              '$s0$e0801$aCtQoyvg1Z/ZoYnt5cFyng==$/2Osr1TKGNgv48DXGmSvIHPFzO3aaZHuw1KQRIDvJR4='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '54ca8389-588c-4170-a8af-a9725384c867', 'Mrokslamard',
              'Drakmaracder', 'Jagcreser', NULL, NULL, '11.10.1957', NULL,
              '$s0$e0801$YI9Qx6RuEl6ZJEG92g/T5A==$NVDP9ZEE5+WKZiBoFsvIwIpMWxL1Se3kvJLh2n5fXJo='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '7f821de4-0bff-47e7-b694-3f44fae8e131', 'Mararcmur',
              'Azurcrazur', 'Azakmurure', NULL, NULL, '16.02.1969', NULL,
              '$s0$e0801$Y+gmOEEs01DyG+ktd+eBUQ==$PqYeA9EAgg7o3HWFyKYhA88ZdQh8IvA422sekbrvKmk='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '77042391-40f2-4aa1-826f-3f0f99fb217f', 'Lukmirimur', 'Zedarkd',
              'Drakzoirure', NULL, NULL, '04.12.1957', NULL,
              '$s0$e0801$5P0ia4RDjlKqyWGdl0hpqw==$SKIkkxIs07tAHeOFbkDvERoBQw0PEeSDZwtS5LG65Is='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '28f9d1c0-0e64-4649-b436-75810924fc44', 'Zurclozur',
              'Maraircred', 'Azaksalmarer', NULL, NULL, '08.08.1951', NULL,
              '$s0$e0801$dT5tPUvo237noHLVeuRNKQ==$eR9ApjUCJbniCvcAPpVaBehPNnEMnSTDcAsLiHynQRo='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'e0c2b803-2483-4d23-86ae-7a36e203e9fb', 'Reimurzur',
              'Madarctron', 'Mermid', NULL, NULL, '09.11.1949', NULL,
              '$s0$e0801$rxWa17Frh/e6r2fQcn7p2A==$xrny3mgOAq46bF/og5yK82bkrsePsp1fF4T6oU3SlIw='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'bdb0bd90-f92e-4bfc-a803-76a02df0fe7c', 'Zorksalmartron',
              'Crocresed', 'Cryarccred', NULL, NULL, '17.04.1941', NULL,
              '$s0$e0801$BoAVnEWuQ6TPbedDsRusRA==$Buza71cjG0pbZ88p43Atj5kl2njKjtdFiZ/sOBye/wc='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '1210c0ef-fcfb-4b34-8b20-deea740dd447', 'Cruslamard', 'Creoired',
              'Cryarkd', NULL, NULL, '09.04.1950', NULL,
              '$s0$e0801$o2VD/FmHSQ58NxqA8Am0pw==$o0HgUerN2omwnWfgjmCStY1vyhV7x1UcqFK2KV5XQXc='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '36f8c214-e51c-4d4a-a24d-b208783efebc', 'Rayarkd', 'Drakirmed',
              'Bremirid', NULL, NULL, '23.08.1983', NULL,
              '$s0$e0801$IYpPShFNL6/p4XV9XWvAuw==$S5tUsLG+FMwK4JwC2eNL995n0pEcUIiIkLxIC7HwbQo='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'ca960b45-6e68-46a9-94e0-d6085f4fe8a1', 'Crocresarc',
              'Cryarcmed', 'Rayarkd', NULL, NULL, '05.08.1955', NULL,
              '$s0$e0801$6JV274TkKUJnwQaXCykARg==$aau/O2MglksQCL33KZN9Cuuz9wIfsmVbPtQn4+lAkSk='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'ab3a30a3-e91c-4be1-9f13-44dc02e06ee7', 'Mrokmaracarc',
              'Mjolurakarc', 'Lukzertron', NULL, NULL, '31.01.1944', NULL,
              '$s0$e0801$UasvlHiO4yB83wPtU7B24Q==$cU3rk3dF66o6fqJFGLHPa0areVsf4+fAI1rzyjZCLAI='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '79b35450-6f9e-4417-ab5f-4a9f3b388988', 'Merurakarc',
              'Cromurmed', 'Crusalmarmed', NULL, NULL, '24.07.1978', NULL,
              '$s0$e0801$qtrk0p43UguRs3mAAILsQg==$gKiRergk0gtCoTZ3I3mi/W1zyWQZAggbXRVD3QI1hnk='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'f7eeaa2b-9f63-467b-b021-0b1a08f4f616', 'Creoarcarc', 'Zurcraes',
              'Mjolirarc', NULL, NULL, '29.07.1952', NULL,
              '$s0$e0801$wHvTmDYSsDGiBCWrVXRmnA==$E0LDI0y39iPn9gonvBivRiLNM8f30FPjPqOzVNt2Vqk='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '635d8296-90d6-4858-b4c7-c1e8a92614df', 'Cryzoirer',
              'Madcracred', 'Cruredes', NULL, NULL, '13.02.1942', NULL,
              '$s0$e0801$ongHDjOZ+frawrd2OKkwhA==$27Hs4zKRB8mdA0NICtzvIhRTpQ7ukuerTSqFxc7HiWw='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '4ee01136-fbf8-4319-a17a-dbb756977c3e', 'Creocresed',
              'Madurakmur', 'Zurmiarc', NULL, NULL, '30.09.1975', NULL,
              '$s0$e0801$0ic8VUMaFagwPX4o2IB7HQ==$/mUqoIVEvHwq6CIZUzD4LQsK+u7+/WwAAtiv9z8bNeg='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '2d341663-69a9-44a9-88ac-12792627b67d', 'Azurarcmed',
              'Drakslamarcred', 'Zurzoirarc', NULL, NULL, '08.03.1944', NULL,
              '$s0$e0801$BDYPlvRu7m0tFjLje+A5Cg==$3TsLXr6LfX/7t3tQ12Jp5hL5SUQPqInMau4jp91uFjw='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '9fd0aa56-ba25-4b17-9988-0945cfca443e', 'Krzermed', 'Cazered',
              'Ramurd', NULL, NULL, '13.12.1948', NULL,
              '$s0$e0801$xGlNyAMTk8qGXJbzsJlKHw==$2P6rW5eny1I1Oqm/vvo/uM57vEmq5RspqdvaVWb0Jo0='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'abf22620-a2ed-4c49-8932-bd3e573962b4', 'Cryarkcred', 'Cryarcd',
              'Cromiricred', NULL, NULL, '24.12.1959', NULL,
              '$s0$e0801$+s6zt3KGOSM0uxUD5DxrzQ==$TuHdIbFQWBcY7X2K2zFYgramVlk9dDrIznfLuaFFb+g='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '9b860bd4-662c-468e-93c4-0ab3f316dadc', 'Mrokairder',
              'Creomaracder', 'Azurclotron', NULL, NULL, '18.11.1985', NULL,
              '$s0$e0801$Jdbg9Cl5sIjdz69dkiXZzQ==$ZUJkQejeaM/dCbGg7ZxVLzAEuWxxovu/0AtO1dilme0='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'd6b238d4-ec27-4b9f-b97d-12df48539131', 'Breurakure',
              'Creoairer', 'Drakcramur', NULL, NULL, '06.12.1962', NULL,
              '$s0$e0801$6ZuuY0fb0CHCDub7Wdz7hQ==$mCfIhGIgChJHweI/bmgWjkR1xbLvW8KYTRq2IZ8DL/Y='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'e6c5cd48-1657-4e83-b292-64f96283e31c', 'Racresarc',
              'Raycrestron', 'Lukloricred', NULL, NULL, '10.06.1972', NULL,
              '$s0$e0801$ooFdU9amtyQnR9WMXeqRQQ==$omb8JMqk0CjOhL3/wYC7ao1W47X+idG45NMNpnc9LcE='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'e48f3b3d-733b-4b4e-9ac9-f3e5c3b8301b', 'Reiirder', 'Reizered',
              'Reimaraced', NULL, NULL, '24.09.1988', NULL,
              '$s0$e0801$lWwzVCXIFHlmAw3Aj8G4aA==$NMbubGjHXbUVPndDNBtaY12uqSdhealUVoErNlbGRqs='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '2030bbff-8fae-4106-8106-2aae240a34d2', 'Crusalmard',
              'Raymeecred', 'Drakmirier', NULL, NULL, '17.05.1956', NULL,
              '$s0$e0801$0gJk8mL5r88FEBKPtr2lLw==$GlEfIMLEXV1T9l9etxcNq8j2poBlL96PIBkA4fIK4cA='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '3e28f8d5-2c5c-49cf-a8f1-717947291f6b', 'Raymurer', 'Lukmirimur',
              'Mjolloritron', NULL, NULL, '07.12.1990', NULL,
              '$s0$e0801$TytJw3zLbSpdIF2ZkRy1lw==$WYD8jOy2SpLTep/MBRyhjeUvaAm8JYXNQG0jGx+IHnc='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '252ac7b9-aa73-4dd5-9f20-40252d6dd3e5', 'Azurcraark',
              'Mrokmirizur', 'Marmider', NULL, NULL, '20.09.1973', NULL,
              '$s0$e0801$koUHuVqOJ2NBm+kxgGS8Kg==$DS9z5jejgoWBvWNwoO+QwGbexjARD4EJxMUuVPQCZiE='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'd1eb03a0-e7b9-4ab9-9a3c-cbfc6aef7b00', 'Mjolires',
              'Mrokslamard', 'Lukurakarc', NULL, NULL, '03.06.1961', NULL,
              '$s0$e0801$7Zufv5ftscfJk9nPnp1e1w==$NZ6qVPoWc9G+2RmzLZxPM1d7XEdX5L5fVfFN99SvOis='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '8f29d8db-6faf-4ce6-8af7-cb2f49651d66', 'Cazerark', 'Krmeeark',
              'Jagarczur', NULL, NULL, '15.04.1946', NULL,
              '$s0$e0801$D0iBbNDZZB7hZVqcaGlOZA==$GtsHib3ADV9Y/jPVhKZuSE01UGBP+i+4M1RcCk9lSow='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'bc9e8750-5d94-4c95-a729-cb3523bd8833', 'Mrokmeemed',
              'Azururaktron', 'Reimaracark', NULL, NULL, '16.05.1981', NULL,
              '$s0$e0801$MLXEjYD8ObE/Hc/sfL7Jvw==$U5UwZ5D20MRgj+KZUYuNVUS3jAZ+E5MtV6XYK6YAblM='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'd7f58ca5-0920-45fe-ac69-dfd4e8e1724e', 'Merreder', 'Caloriure',
              'Azakirark', NULL, NULL, '02.06.1952', NULL,
              '$s0$e0801$ZP0DvXLM/lO6MprxuB27lQ==$qHU2bsVsFob8MeJ2GwIEVA3NT780vxG+3XHTg/7eZ1o='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '594cd079-57df-4e04-9091-a897ab764a7d', 'Mrokarczur',
              'Zedurakcred', 'Lukmaracmur', NULL, NULL, '09.09.1973', NULL,
              '$s0$e0801$0xlbnAFpG+01fy95Gm2JcA==$83pW4r8AEr/v35/U/fNh2+Z5uszCGUjmHEiV6RN+Wgw='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '50344aa4-1aeb-485d-ab00-49e7f7a14352', 'Ramiriark',
              'Drakmaracd', 'Mrokmirier', NULL, NULL, '26.11.1943', NULL,
              '$s0$e0801$D7OX1OgoXwdzVCJblbEeUw==$llRktQV1KrDlo2YnGx9X7gC0QjRwvNLUiTr6TQ+JzKw='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'edbc3268-94f3-42b5-a7dc-4f002fbb0287', 'Meraircred',
              'Breclomed', 'Mjolsalmared', NULL, NULL, '15.08.1946', NULL,
              '$s0$e0801$36Au8eOMRldiNxtwq8CsIQ==$O7VGxtgFGJ8rNE5rcIvTgY/STcTl77/ISTpCRYzebP8='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '3f56e349-24ca-4458-bd0f-84ee8baf5f83', 'Reimier', 'Morlorimur',
              'Merarkmed', NULL, NULL, '10.04.1986', NULL,
              '$s0$e0801$mgoEf2aEHL1l1OEtwaEu9g==$m7PS/S8DF+ThXEzKYJRr1nmg9tuqk+ZTDbyuNxTC4P8='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'ff4921a2-12e7-4176-bbb7-d37763ee24ce', 'Rareded',
              'Mjolslamares', 'Zedslamarder', NULL, NULL, '21.01.1982', NULL,
              '$s0$e0801$acydlrT9UvlFUcbQq7gHJw==$v76S1X3A7aw1kVRq+Axe2gDBtq8fUfSKx8+iIdaHSYQ='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '8816ce29-2ec3-4472-ae52-462b6cb8a0f5', 'Zorkreder',
              'Azursorarc', 'Lukslamarure', NULL, NULL, '11.12.1942', NULL,
              '$s0$e0801$3JEb0YbQGJmStkTb/dXv1g==$YHSTpL5BMqk5zvSGMLSetQ6Wr2AbkpwiRe/1QnvFbzk='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '02ad1ba1-0190-41e5-97aa-b4c2cb359705', 'Caslamarzur',
              'Jarcreses', 'Zorkmirier', NULL, NULL, '23.03.1976', NULL,
              '$s0$e0801$JK3BJZ0ZMFepTDEZU2FkbA==$2/MxOzHZmIuBhAt0S7tFdw7ivKycwyHordCoBffTN94='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '366f8ee9-4604-4557-b976-ed5de297d3c1', 'Mermitron', 'Zedcraed',
              'Bresalmartron', NULL, NULL, '20.04.1977', NULL,
              '$s0$e0801$Y8TWlGFi/wkO8lWpqD2wvg==$z+m8wZpYQ+2f/W6Jj5tQLChbgRbjQHm6XIoXLyH/XgA='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '8ccbbdf6-8c55-4852-bad1-4c535d0c1947', 'Azakclocred',
              'Mrokslamarmed', 'Morslamarzur', NULL, NULL, '23.08.1940', NULL,
              '$s0$e0801$X3x4OyZehpRGIqy0+FaAcQ==$W3jZxVHyzO/O3WdIeYTAqiIK78ZNjvI+eq1/kh+ofx0='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'd2b82c39-aa11-4c02-81d5-a561c91d0023', 'Reilorizur', 'Reiaired',
              'Mrokmeezur', NULL, NULL, '08.08.1958', NULL,
              '$s0$e0801$fIoi6+Mpn8C+4OOE3awqUQ==$GShLMo3E77g9/IoueU7zygncsTch82FnNcouE6lI6ys='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '1a9645b2-cc5f-4450-adf3-341c4fb8ceaa', 'Rayird', 'Marsores',
              'Azursores', NULL, NULL, '23.11.1967', NULL,
              '$s0$e0801$QQSv1hvXIF09Sgxsk+Ur3A==$XcLSd0bSeGtBv/mZA56JPlQLq42rd6oQZvKd1FwdMuc='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'b149f995-9946-461c-a43c-d84474ca1f37', 'Ramurarc', 'Marmurarc',
              'Cruredmed', NULL, NULL, '03.08.1984', NULL,
              '$s0$e0801$rTrqXpru3mlLpA4l383qHA==$2IwJqZy7QpygaWwwvJw90ID8ehDNemK/L4j/+PBAjg8='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '1f0b83b1-78da-43c4-8bd2-d2d67231a44e', 'Brecresd', 'Breairark',
              'Jarsalmared', NULL, NULL, '20.05.1961', NULL,
              '$s0$e0801$cB+llVFxEndReDKkdIAV1Q==$wcAjK3f7qQYC8G2MHC9l+dm1N2u1zh/jAW4/zjYCKWc='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '8537457c-9630-4577-b4c5-0695db2c7507', 'Merurakmed',
              'Mersalmared', 'Morzeres', NULL, NULL, '01.12.1966', NULL,
              '$s0$e0801$wJGikP+5JuRbALX0T0oZVA==$EPMez/w59HJaMxJkD3/77bAMNUey/CQrj8QLlbjW2YQ='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'b2f357ec-5819-43c3-b781-e1fa43f85888', 'Lukmeeder', 'Krird',
              'Raymurzur', NULL, NULL, '09.05.1963', NULL,
              '$s0$e0801$SDY8mEkjfW9ihAAXhtQXwg==$wHqQ3C+ccubf5XhUnNIkx9upMAm0slkXlMJ8QTnUP6c='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '5b96473f-b081-4ca6-8b12-a4b42b631e80', 'Zurzoirure',
              'Cromaractron', 'Azakarctron', NULL, NULL, '11.01.1986', NULL,
              '$s0$e0801$n88EMzz5QHXdsO0bdTLTCg==$d/rtBiGVbwQbw4cPcF71JepB/daVxKXScXjt2FKWv7s='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '51714c73-b08c-4166-9a28-8f4447863374', 'Casalmarzur',
              'Crymirimed', 'Mrokmeees', NULL, NULL, '17.06.1942', NULL,
              '$s0$e0801$6XnGuFvuklCt05YqP3693g==$71ajS8S24+AVZsFAo8U9SsFcFo59HppLSXR8cAeM1eg='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'a4b50e1d-d468-4065-9378-5924e07239f7', 'Creoarces', 'Madsorarc',
              'Azakmurmur', NULL, NULL, '28.08.1967', NULL,
              '$s0$e0801$WOsOmC/sROqNpBqYyZCnKw==$TvIzlnbHv4KO5LGFvTyeUmNxrHTQEZJkEWPyL9NmZDI='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '84d5a7af-e3ca-4429-829a-3fa019d9fca0', 'Crumaracure',
              'Caarczur', 'Lukloriure', NULL, NULL, '08.10.1953', NULL,
              '$s0$e0801$z4XXx6Cnxxe56FbYatBCbg==$P1jSPNmo6N0K7z8KGNi/d/nG9Wj6alH6bqygf/TL3dc='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '0506b446-66fd-4b0d-9e06-81cb98777a31', 'Crulorizur',
              'Madmurder', 'Lukclozur', NULL, NULL, '16.02.1964', NULL,
              '$s0$e0801$fU9hO/L9/B8sBOjTpkgEfQ==$DKJbimbvxQS0RhJnt8pk4MYrEH60uEA/iYpa9I/xPUo='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '0491f6e0-f9b5-476f-91a4-9000c2c996d4', 'Crocraark', 'Rasalmard',
              'Creomirimed', NULL, NULL, '19.07.1958', NULL,
              '$s0$e0801$svHTCryd1ekGeTRq7D6M9w==$iw7j03s4XlyyzUWTyQpL0x4MiJubMZuzTyhh4szKHpo='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '99bbb18f-efe2-4c70-865a-5e65f81aa3e5', 'Zedurakarc',
              'Drakircred', 'Zedurakder', NULL, NULL, '21.09.1975', NULL,
              '$s0$e0801$fL/EbShXkhBDzhiQs3zx8g==$SmaF75gRR7fA9r0hDHL3w9FA/4q4Luf2uLUWn6o005o='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '2eceb3ef-83b3-40ff-be5c-10177e993041', 'Mjolmeeark',
              'Zedsormed', 'Madcraure', NULL, NULL, '10.02.1970', NULL,
              '$s0$e0801$fEbFXcICCLDPG3uqNPrYZw==$BcPyBoNKokY5ed0KO6hG7jwXx//WI/0yQBQltRoVGJE='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'b626f2d2-8759-4040-8083-b0991b95b5b2', 'Lukmiriarc',
              'Creolorier', 'Cairarc', NULL, NULL, '25.08.1948', NULL,
              '$s0$e0801$5N99BOn65maHPeNZ1+JfOg==$Cu3TDOfXtKztNfXPCsuN7mOfhxl2Gg/U5VJRwIFAogM='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '7238a327-a8c1-4d98-84e4-664a8d07c814', 'Zedirark',
              'Zorkmurtron', 'Raarcmed', NULL, NULL, '02.02.1965', NULL,
              '$s0$e0801$S4vc8Qr+AaP9AwK1Aesruw==$EUc7KEMF1S1Nt62UVuVW9gSZqkJE57hl6kyEaH1OYas='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '53353763-65f1-4cb3-8a5a-005948565e14', 'Mroksored',
              'Mjolmeemur', 'Morclomur', NULL, NULL, '27.09.1962', NULL,
              '$s0$e0801$86GnaEAJXYWqYTaxbFf5vg==$LGkM3XlkAYg1AcEOgpNep+ztLUD11i4Rn9EkTudiDHI='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'da24fdad-2aaa-4374-a5c2-1e48a0ea6484', 'Cryurakes',
              'Lukcresure', 'Lukmirider', NULL, NULL, '14.08.1960', NULL,
              '$s0$e0801$toGupzI7MZxykkVXZUoPqA==$KJvLUftk3jKZgdYqKfcC2ffgAj4Oamdu7iAzpzvCxx4='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '960b9538-6a99-4538-b24b-c22e49aab823', 'Zurmurer', 'Azurredmed',
              'Azakclomed', NULL, NULL, '15.10.1967', NULL,
              '$s0$e0801$DMiX/TX6ygxvnlSkXzrHag==$EGjLlfMYVB7vrF8loCqGPQt3N5346aAsSrNKzP0RidI='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '86152ed5-3969-4671-ba04-ccd266e756db', 'Zorkmiries',
              'Zorkirmed', 'Mjolaircred', NULL, NULL, '03.09.1982', NULL,
              '$s0$e0801$1UQ84CGOMIQ3eFNkz0yiqw==$/YQT3GVekVyJHF1jghGuIE2i/c3mw0bLEk0I4ftMLhg='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'fd987893-aab3-4b0d-8b7f-dae203f78476', 'Reiuraktron', 'Madmurd',
              'Crusalmared', NULL, NULL, '06.05.1987', NULL,
              '$s0$e0801$PH8DwpQFDjPpp7AcWNQIyQ==$bP2nNbgVgeKansZgkv2JfXODi7ePuRCk7UpP2qtI3Kg='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'cbf5ff55-e1a3-4b5e-81c1-43288a4c5938', 'Zurlories', 'Raredarc',
              'Reislamares', NULL, NULL, '03.04.1990', NULL,
              '$s0$e0801$6Utv/xkiJD7rbYRwD0qKpg==$ggb+hJAeW/w2xvbApzkG5C0QLjcoF9uI6h5m42AVFbE='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '2f7c56bf-7876-4848-acab-9c69fccb1066', 'Mrokarcure',
              'Luksorzur', 'Krclocred', NULL, NULL, '12.02.1974', NULL,
              '$s0$e0801$YyoP3rAYxlFnFipoHVjkcg==$HMbnjT0g9Y1lccO4FvHhdQVJ3/r+hoWBGly8NOSPquE='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '209f284a-fef7-4c13-aab5-769d89267a11', 'Jagsorure', 'Zurmirid',
              'Cryarkmur', NULL, NULL, '03.04.1951', NULL,
              '$s0$e0801$q68DFwaZwoYj7RlShUxe5Q==$lFxdbqz+Hq5d6tlNyNXEHlbOuv1FvtAzCglK8GG6gts='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'c0147a87-25ee-4b3d-9428-14ba6e77af9e', 'Madmitron', 'Ralorider',
              'Raairzur', NULL, NULL, '10.05.1949', NULL,
              '$s0$e0801$Z2yhjgu9r8+Z/rhY6/CE+Q==$yZQEP2nEztfaHOwBjiISuoExfRyqO9vrbEIIG2kGjpI='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'c9b34b56-5e24-4361-845e-170921c69823', 'Creourakark',
              'Zurarccred', 'Reimirier', NULL, NULL, '05.11.1983', NULL,
              '$s0$e0801$aCo56M4zRfCVl+VnI7AX6A==$pVd9EquhSNXW0WlaIxcT2GI9YTgBNok7bsTWgNCbn4k='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'a87c7a34-870e-4eaa-813f-9a4dd3fab571', 'Brecramur',
              'Reiurakcred', 'Morirer', NULL, NULL, '20.06.1977', NULL,
              '$s0$e0801$uyPnLLpt2Hn5wZgJTJz/bw==$hhg982GRK5PXpNv7e14PHz5N0aQvIVXfve+MKsTx8zc='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '57a368b6-1d17-40f9-9097-dae8f91d2901', 'Crosorer',
              'Madsalmarmed', 'Cairmed', NULL, NULL, '06.10.1986', NULL,
              '$s0$e0801$1U9CAut3RzRUxvpe70WUSQ==$S52QXe7o4RwTdeUBLhuLKwSWYB22365K+Mjw9fUcIIM='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'd2b4b8ef-249e-493b-84a7-62769362c89c', 'Azakmaracd',
              'Azurairark', 'Zedmiarc', NULL, NULL, '12.10.1945', NULL,
              '$s0$e0801$0qm5YlWDuWv5oLiqmpbBpA==$3LEu+gfS4uHR1ZQQLnHcYP6hTliosvl3Yfv2T22HqNM='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '1cc9f22b-16fb-4415-b70c-12e8b08afb4f', 'Jarmeeark',
              'Zedcresark', 'Caarctron', NULL, NULL, '31.03.1981', NULL,
              '$s0$e0801$7PPC33snx/MI0+nqeBbi9w==$6A4jxhEcw9iI0ikMpgqS16loWaCcj2oEHVOFwXw/0iI='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '72bcc148-1c2c-40dd-99af-d57b8308dbbf', 'Drakairzur', 'Raymimed',
              'Mjolcramed', NULL, NULL, '11.07.1966', NULL,
              '$s0$e0801$Zqn6TKjED40sXIIzLesdpA==$KW5zXry950yZse7iRsK0HrWuOaSYFyIGuxkz6nyCKaY='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'c2bd0690-113b-4cee-b6db-d1ab576de1ef', 'Caarctron', 'Morlorier',
              'Azaksord', NULL, NULL, '31.05.1949', NULL,
              '$s0$e0801$kWxMeD1gcW5/kiV5vfKvCw==$kCcu2M+wY+2sHfafbp8ZKc56MUaY/OGySve937jjd4o='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '83cd5130-d9c9-4145-986f-8d63a2f34c5b', 'Creomeeder',
              'Cryaircred', 'Drakmimur', NULL, NULL, '05.06.1975', NULL,
              '$s0$e0801$5R1vWwNFX0hcaa60jtQVfA==$p2wfgv1968eU5wZN0dAJ9wr6vLjmHWkxOzlFUZJR2Co='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '6801ad65-c107-4631-b281-ee2b209abc68', 'Merclomed',
              'Azakarkzur', 'Zorkairtron', NULL, NULL, '29.09.1947', NULL,
              '$s0$e0801$P9g/1c1FfuB6Ejb88wq6Qg==$iNF21WztmB35RM7EAUhUzegw50Z+U1LiPsKka5TnErU='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '16e7aaec-2a58-4ab7-8ed9-a09e8d5db19f', 'Krslamarcred', 'Madmid',
              'Azurcloarc', NULL, NULL, '10.10.1978', NULL,
              '$s0$e0801$OQDcfvn1jnXiw4Pi+ZHClg==$GdTsuI1W4o5/iLf2qpy/w+QIehz/VyUfOcU8BXaYvlM='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '66031cf7-eb2b-454e-9761-43484435775d', 'Creoairark', 'Cruzerer',
              'Mrokcraark', NULL, NULL, '23.08.1948', NULL,
              '$s0$e0801$3N3e/+WOJgB9pg6wBTig8Q==$4Wxu9gDtyNCwfqiPW2FQCtvMxoHipv+3mzOcKNUA2Bo='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'af0a46f2-62ed-4e13-983e-7790a65f14e3', 'Jarslamarcred',
              'Crosalmarder', 'Merclotron', NULL, NULL, '12.07.1944', NULL,
              '$s0$e0801$k2U9kl+1+Ptyh0UmiFEugA==$PHXd8aeKanCVhgBwvMMd3wphYuYCkI2DqMxPSTv7Znc='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'a9f5b505-1ba3-42fc-a8c4-3f5689b1dc69', 'Rayslamarark',
              'Reimaraccred', 'Jarmirizur', NULL, NULL, '16.07.1980', NULL,
              '$s0$e0801$9SsSXFQprgG2mLsQoyPmpQ==$PG/PUlGut1SQu/bDMVZ0qmf7680jVKWuDtSKIkHN5Cg='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'd5bebc62-0062-4486-88d2-8ffc2dbdbfcf', 'Brearces', 'Mjolircred',
              'Zurmirider', NULL, NULL, '16.02.1977', NULL,
              '$s0$e0801$y3hPUi557e55y1aJol1MMg==$3BXdGlrcXmPy1QmJ2E52zFTex2t3C/kuu7yD+pAa8/4='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '0e4ad1cf-2b31-4378-ab6b-d2254e78e106', 'Caairer',
              'Mjolsalmarmed', 'Jarmurder', NULL, NULL, '02.08.1954', NULL,
              '$s0$e0801$1sz0KVNFsC46ILG3cy3HTA==$UI9MX+vSshRt0GeL4UVY8/PPDiJBbRIEKhBt4lqHnoI='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'b07af3d8-fee6-4f03-a435-8aa3becfc4d4', 'Creomaracder',
              'Creoarker', 'Raarktron', NULL, NULL, '14.08.1969', NULL,
              '$s0$e0801$A2jVenDDSbB1RtbHQab+Jg==$zfJ1KW/IlffzN8M+JPUcvmmTD9GIZ6fiqYif1ndV6XY=';

INSERT INTO lawAndSocialDb.school (id, user_id, name, country, city, yearFrom, yearTo)
  SELECT nextval('lawAndSocialDb.school_seq'), 2, 'School1', 'Russia', 'Moscow', 1985, 1990
  UNION ALL SELECT nextval('lawAndSocialDb.school_seq'), 2, 'School2', 'Ukraine', 'Kiev', 1995, 2000
  UNION ALL SELECT nextval('lawAndSocialDb.school_seq'), 1, 'School3', 'Russia', 'S.Petersburg', 1990, 2000;

INSERT INTO lawAndSocialDb.university (id, user_id, name, country, city, yearFrom, yearTo)
  SELECT nextval('lawAndSocialDb.university_seq'), 2, 'Harvard', 'UK', 'London', 1985, 1990
  UNION ALL SELECT nextval('lawAndSocialDb.university_seq'), 2, 'SPBGU', 'Russia', 'St.Petersburg', 1995, 2000
  UNION ALL SELECT nextval('lawAndSocialDb.university_seq'), 1, 'Univ3', 'Ukraine', 'Kiev', 1988, 1994;

INSERT INTO lawAndSocialDb.job (id, user_id, organization, position, industry, website, yearFrom, yearTo)
  SELECT nextval('lawAndSocialDb.job_seq'), 1, 'Jason Stathem & the partners', 'Lawyer', 'Entertainment',
    'http://awesomecompany.gov', 2011, 2016
  UNION ALL SELECT nextval('lawAndSocialDb.job_seq'), 2, 'Private practice', 'Lawyer', '', 'http://ThePirateBay.org',
              2014, 2017
  UNION ALL SELECT nextval('lawAndSocialDb.job_seq'), 3, 'Conspiracy GMBH', 'Consultant', '', '', 2007, 2017;

INSERT INTO lawAndSocialDb.contacts (id, user_id, email, phone, website)
  SELECT nextval('lawAndSocialDb.contacts_seq'), 1, 'testMail@gmail.com', '+7999111222', 'johndoug.com.ua'
  UNION ALL SELECT nextval('lawAndSocialDb.contacts_seq'), 2, 'test@mail.com', '+7226663331', 'tesuserwebsite.com';

INSERT INTO lawAndSocialDb.follow (user_id, followed_user_id)
  SELECT 2, 1
  UNION ALL SELECT 2, 3
  UNION ALL SELECT 2, 4
  UNION ALL SELECT 2, 5
  UNION ALL SELECT 1, 2
  UNION ALL SELECT 3, 2
  UNION ALL SELECT 4, 2
  UNION ALL SELECT 5, 2
  UNION ALL SELECT 6, 2;

INSERT INTO lawAndSocialDb.message_history (from_user_id, to_user_id, date_value, text_value)
  SELECT 1, 2, 0, 'First Message from 1 to 2'
  UNION ALL SELECT 1, 2, 0, 'Second Message from 1 to 2'
  UNION ALL SELECT 2, 1, 0, 'First response from 2 to 1'
  UNION ALL SELECT 2, 1, 0, 'Second response from 2 to 1'
  UNION ALL SELECT 1, 3, 0, 'Message from 1 to 3'
  UNION ALL SELECT 2, 3, 0, 'Message from 2 to 3'
  UNION ALL SELECT 2, 6, 13, 'Test 2-6'
  UNION ALL SELECT 7, 2, 31, 'Test 7-2';
/*
INSERT INTO lawAndSocialDb.user_school (user_id, school_id, yearFrom, yearTo)
  SELECT '4', '1', '1994', '2000'
  UNION ALL SELECT '4', '3', '1955', '1960'
  UNION ALL SELECT '1', '2', '1984', '1940';
*/

