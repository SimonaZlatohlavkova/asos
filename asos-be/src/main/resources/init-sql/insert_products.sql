INSERT INTO "products" (id, name, url, description, original_price, stock, created_at)
VALUES
    (1, 'Yogurt', 'https://res.cloudinary.com/riqra/image/upload/w_656,h_656,c_limit,q_auto,f_auto/v1695769979/sellers/9/rlzngadefxo9zlc0zigq.webp', '88% milk, 2% strawberry, 10% chocolate', 1.20, 8, CURRENT_TIMESTAMP),
    (2, 'Yogurt Sophie', 'https://www.dairyfoods.com/ext/resources/eNews/2012-01-np1-422.gif?1327517141', '88% milk, 12% chocolate', 0.90, 8, CURRENT_TIMESTAMP),
    (3, 'Yogurt Danette', 'https://digitalcontent.api.tesco.com/v2/media/ghs/8ee08181-50e6-40b2-8f0c-f0e3d5aa2c4a/443e9ae8-18de-4786-a918-f29198f621f0_121963493.jpeg?h=540&w=540', '50% milk, 10% dried banana powder, 12% chocolate', 1.19, 8, CURRENT_TIMESTAMP),
    (4, 'Yogurt Jogobela', 'https://lunys.sk/wp-content/uploads/2023/03/640015ea09c28.jpg', '70% milk,  25% cherry, 5% sugar', 0.76, 4, CURRENT_TIMESTAMP),
    (5, 'Yogurt Pribinacik', 'https://images-polarfood-cdn.rshop.sk/lg/products/796d9ec06da7d5b306b997531753f733.jpg', '80% milk,  15% vanilla extract, 5% sugar', 0.89, 0, CURRENT_TIMESTAMP),
    (6, 'Yogurt Greek', 'https://cdn.mafrservices.com/sys-master-root/ha9/h4a/16000929464350/1590713_main.jpg?im=Resize=1700', '100% milk', 0.80, 8, CURRENT_TIMESTAMP),
    (7, 'Yogurt Parmalat', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTioE4Tg7CS6POKbpDQ3yebxDo2dyPPX7gowA&s', '60% milk, 5% sugar, 20% raspberry jam', 0.95, 12, CURRENT_TIMESTAMP),
    -- Pastas
    (8, 'Pasta Pastianno Penne', 'https://5.imimg.com/data5/SELLER/Default/2023/6/315408690/VW/IS/YR/68365182/pastiano-1-kg-penne-pasta-500x500.jpg', 'Premium durum wheat', 1.50, 20, CURRENT_TIMESTAMP),
    (9, 'Pasta Morton Penne', 'https://5.imimg.com/data5/SELLER/Default/2021/2/RF/YD/RP/18337289/500-gm-morton-pasta-penne-big.jpg', 'High-quality penne pasta', 1.30, 18, CURRENT_TIMESTAMP),
    (10, 'Pasta Italia Fusilli', 'https://www.onelink.pt/wp-content/uploads/2024/01/o12656-massa-fusilli-classic-italia-500g.jpg', 'Classic Italian fusilli', 1.40, 25, CURRENT_TIMESTAMP),
    (11, 'Pasta Sunbay Fusilli', 'https://5.imimg.com/data5/SELLER/Default/2021/5/UE/MX/MX/51654654/sunbay-fusili-pasta-500-gm-500x500.jpg', 'Fusilli pasta perfect for sauces', 1.35, 22, CURRENT_TIMESTAMP),
    (12, 'Pasta Colavita Spaghetti', 'https://yummybazaar.com/cdn/shop/files/GFM-101056_50738a1a-fdd1-4b6b-baf3-df54bab338eb_1024x.jpg?v=1704219210', 'Authentic Italian spaghetti', 1.50, 30, CURRENT_TIMESTAMP),
--     -- Milks
    (13, 'Milk Super', 'https://lh4.googleusercontent.com/proxy/1uVDUrgBxb-iYcHUzwqBnT79GgFz9KQ3r9RtHTk6Lf3YHkf5tGKOk0G7eNRLz5Rwq1x8k1HIJu16D5pjO17aSzaMfTUU0MqtTMEdEOkcUX4_mr3aeCP71cpzG3x1ROjYOg', 'Full cream milk', 0.80, 15, CURRENT_TIMESTAMP),
    (14, 'Milk Rajo', 'https://www.lamitec.sk/_site_media/com_eshop/mod_product/img_xl/PT/PT421007.jpg?mtime=1681453050', 'Slovakian milk', 0.90, 12, CURRENT_TIMESTAMP),
    (15, 'Milk Coconut', 'https://cdn.myshoptet.com/usr/www.jasomzdravie.sk/user/shop/big/33771-1_35747-1-xl-balenie-kokosove-mlieko-na-varenie-17-19-cocoxim-12x400-ml-halal.jpg?671a4aec', 'Rich coconut milk', 1.20, 10, CURRENT_TIMESTAMP),
    (16, 'Milk Cacao', 'https://digitalcontent.api.tesco.com/v2/media/ghs/dd5d3176-f220-4326-98bd-d480e3629c6b/6e6956ec-4993-4802-baf7-e9bd77dc32f4_710039250.jpeg?h=540&w=540', 'Chocolate milk drink', 1.00, 8, CURRENT_TIMESTAMP),
    (17, 'Milk Tami', 'https://tami.sk/wp-content/uploads/2021/11/Milk-35-scaled.jpg', 'High-quality milk', 0.85, 14, CURRENT_TIMESTAMP),
--     -- Drinks
    (18, 'Drink Fanta', 'https://cdn.myshoptet.com/usr/www.megacukrovinky.sk/user/shop/big/3926_navrh-bez-nazvu-2024-08-22t151836-066.png?66c73ab2', 'Orange-flavored soft drink', 0.95, 20, CURRENT_TIMESTAMP),
    (19, 'Drink Cola', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcThbpDCP5u13rc6eA5mcXquo3c69lLy6pLrFA&s', 'Classic cola soft drink', 0.90, 25, CURRENT_TIMESTAMP),
    (20, 'Drink Sprite', 'https://lunys.sk/wp-content/uploads/2022/12/62876b5226042.jpg', 'Lemon-lime soft drink', 0.85, 18, CURRENT_TIMESTAMP),
    (21, 'Drink XIXO Ice Tea', 'https://cdn.metro-group.com/sk/oo2/sk_pim_68974001001_00?w=440&h=440&format=jpg&quality=90', 'Peach-flavored iced tea', 1.10, 15, CURRENT_TIMESTAMP),
    (22, 'Drink Lipton Ice Tea', 'https://cdn.metro-group.com/cz/oo2/cz_pim_383730001001_00?w=440&h=440&format=jpg&quality=90', 'Lemon-flavored iced tea', 1.00, 18, CURRENT_TIMESTAMP),
--     -- Crisps
    (23, 'Crisps Slovakia Bacon', 'https://lunys.sk/wp-content/uploads/2020/05/9353045_T1.jpeg', 'Bacon-flavored crisps', 1.20, 20, CURRENT_TIMESTAMP),
    (24, 'Crisps Slovakia Onion', 'https://digitalcontent.api.tesco.com/v2/media/ghs/74a7de9f-3495-4b8b-919a-93d1be45e1a7/85361e92-bfc7-4caf-b9b7-2365a1df8415_1784639061.jpeg?h=540&w=540', 'Onion-flavored crisps', 1.10, 22, CURRENT_TIMESTAMP),
    (25, 'Crisps Lays Spicy', 'https://www.kupsito.sk/image/a.allegroimg.com/s480/11c03d/c64f180b4a888b5ec8a36660c76e/lay-s-lays-pikantna-paprika-pikantne-cipsy-130g.jpg', 'Spicy paprika-flavored crisps', 1.30, 18, CURRENT_TIMESTAMP),
    (26, 'Crisps Trafo', 'https://www.bio-obchodik.sk/sub/bio-obchodik.sk/shop/product/zeleninove-cipsy-trafo-4807.jpg', 'Vegetable crisps', 1.50, 10, CURRENT_TIMESTAMP),
    (27, 'Crisps Lorenz', 'https://a.allegroimg.com/original/113dd6/bc4e454b4cb887b105d6361f677c/CRUNCHIPS-Cipsy-Zelena-Cibula-Green-Onion-Cibula-140g-LORENZ', 'Green onion-flavored crisps', 1.25, 15, CURRENT_TIMESTAMP),
    -- Bubblegum
    (28, 'Bubblegum Orbit Peppermint', 'https://dozsa.sk/5053-superlarge_default/zuvacky-orbit-dr-peppermint-14g.jpg', 'Peppermint-flavored chewing gum', 0.70, 50, CURRENT_TIMESTAMP),
    (29, 'Bubblegum Orbit Watermelon', 'https://cdn-img.horti.sk/imgcache/zuvacky-orbit-watermelon-draze-14g-full-item-356.jpg?v=1693919497', 'Watermelon-flavored chewing gum', 0.70, 48, CURRENT_TIMESTAMP),
    (30, 'Bubblegum Orbit Spearmint', 'https://cdn-img.horti.sk/imgcache/zuvacky-orbit-spearmint-draze-14g-full-item-355.jpg?v=1693919471', 'Spearmint-flavored chewing gum', 0.70, 50, CURRENT_TIMESTAMP),
    (31, 'Bubblegum Hubba Bubba Apple', 'https://lunys.sk/wp-content/uploads/2021/07/Wrigleys-Hubba-Bubba-Apple-zuvacky-5ks-35g.jpg', 'Apple-flavored bubblegum', 0.90, 40, CURRENT_TIMESTAMP),
    (32, 'Bubblegum Hubba Bubba OG', 'https://lunys.sk/wp-content/uploads/2021/07/Wrigleys-Hubba-Bubba-Original-zuvacky-5ks-35g.jpg', 'Original-flavored bubblegum', 0.90, 42, CURRENT_TIMESTAMP),
    -- Coffee
    (33, 'Coffee Dolce Gusto Extra Creampie', 'https://www.lamitec.sk/_site_media/com_eshop/mod_product/img_srcset/PT/PT502035/kavove-kapsule-dolce-gusto-espresso-intenso-16-ks-nescafe-pt502035_01024.webp?mtime=1681453063', 'Creamy coffee capsules', 4.50, 25, CURRENT_TIMESTAMP),
    (34, 'Coffee Dolce Gusto Espresso', 'https://tvojakava.sk/1946-large_default/nescafe-dolce-gusto-espresso-16-ks.jpg', 'Espresso coffee capsules', 4.20, 20, CURRENT_TIMESTAMP),
    (35, 'Coffee Dolce Gusto Ardenza', 'https://lacne-nakupy.sk/images/products-cache/9ca7d9f713ed8b3cf3631d33a804a1d5/111538_w700_h700.jpg', 'Ardenza coffee capsules', 4.70, 15, CURRENT_TIMESTAMP),
    (36, 'Coffee Dolce Gusto Cortado', 'https://cdn.nay.sk/images/product-w510h463/5/1177725.jpg', 'Cortado coffee capsules', 4.60, 18, CURRENT_TIMESTAMP),
    (37, 'Coffee Dolce Gusto Latte', 'https://img-cdn.heureka.group/v1/4efa444b-0ccf-43b2-82dd-e6aa58caa0ff.jpg?width=400&height=400', 'Latte coffee capsules', 4.80, 12, CURRENT_TIMESTAMP),
    (38, 'Coffee Aiello Arabica', 'https://www.iplody.sk/uploads/2023/07/515-1-aiello-kapsle-100-arabica-10-ks.jpeg', 'Arabica coffee capsules', 3.90, 20, CURRENT_TIMESTAMP),
    (39, 'Coffee Aiello Deca', 'https://cdn.myshoptet.com/usr/www.olmikashop.cz/user/shop/big/506-1_dec.jpg?644933a1', 'Decaf coffee capsules', 3.70, 15, CURRENT_TIMESTAMP),
    (40, 'Coffee Aiello Classico', 'https://cdn.myshoptet.com/usr/www.olmikashop.cz/user/shop/detail/503_classico.jpg?64492ffe', 'Classico coffee capsules', 4.10, 18, CURRENT_TIMESTAMP),
    (41, 'Coffee Aiello Intenso', 'https://cdn.myshoptet.com/usr/www.olmikashop.cz/user/shop/detail/509-1_intenso.jpg?6449355a', 'Intenso coffee capsules', 4.30, 10, CURRENT_TIMESTAMP),
    (42, 'Coffee Lavazza', 'https://tvojakava.sk/5130-large_default/lavazza-qualita-oro-kapsule-pre-nespresso-10-ks.jpg', 'Lavazza coffee capsules', 5.00, 8, CURRENT_TIMESTAMP),
--     -- Snacks
    (43, 'Snack 3bit', 'https://digitalcontent.api.tesco.com/v2/media/ghs/07849726-b415-4eaf-892a-0d6e48a83c32/8c6eafce-be21-4123-989e-b6e58d0258b1_803623491.jpeg?h=540&w=540', 'Chocolate wafer snack', 0.80, 30, CURRENT_TIMESTAMP),
    (44, 'Snack Twix', 'https://images-demro-cdn.rshop.sk/lg/products/ac046b9d388332bb6063f43e9c9d9726.jpg', 'Chocolate caramel snack', 0.85, 25, CURRENT_TIMESTAMP),
    (45, 'Snack Margot OG', 'https://lunys.sk/wp-content/uploads/2023/09/6503bf52a79c4-300x300.jpg', 'Coconut chocolate bar', 0.90, 20, CURRENT_TIMESTAMP),
    (46, 'Snack Margot Plus', 'https://www.e-cukrovinky.cz/fotky14690/fotos/gen320/gen__vyr_7148_margot-plus.jpg', 'Extra coconut chocolate bar', 1.00, 15, CURRENT_TIMESTAMP),
    (47, 'Snack Margot Exotic', 'https://www.e-cukrovinky.cz/fotky14690/fotos/gen320/gen__vyr_7145_180222_Margot-Exotic_pack-shot.jpg', 'Exotic flavored chocolate bar', 1.00, 18, CURRENT_TIMESTAMP),
    (48, 'Snack Nestle Nesquick', 'https://www.e-cukrovinky.cz/fotky14690/fotos/gen320/gen__vyr_3886_ShotType1_540x540.jpg', 'Chocolate cereal snack', 0.95, 25, CURRENT_TIMESTAMP),
    (49, 'Snack Kinder Maxi', 'https://d25-a.sdn.cz/d_25/c_img_gR_d/SlJB2AG.jpeg?fl=res%2C350%2C350%2C1%2Cfff%7Cwebp%2C80', 'Milk chocolate bar', 0.90, 30, CURRENT_TIMESTAMP),
    (50, 'Snack Knoppers Coconut', 'https://d25-a.sdn.cz/d_25/c_img_gS_Bw/Z4qY5R.jpeg?fl=res%2C350%2C350%2C1%2Cfff%7Cwebp%2C80', 'Coconut wafer snack', 0.85, 22, CURRENT_TIMESTAMP),
    (51, 'Snack Knoppers Peanut', 'https://d25-a.sdn.cz/d_25/c_img_gY_BX/mh4tGd.jpeg?fl=res%2C350%2C350%2C1%2Cfff%7Cwebp%2C80', 'Peanut wafer snack', 0.85, 20, CURRENT_TIMESTAMP),
    (52, 'Snack Knoppers Dark', 'https://img-cdn.heureka.group/v1/fd090459-f704-4f9d-bbca-25c447196615.jpg?width=400&height=400', 'Dark chocolate wafer snack', 0.85, 15, CURRENT_TIMESTAMP),
--     -- Donuts
    (53, 'Donut Choco', 'https://www.minitbakerycafe.sk/wp-content/uploads/2023/09/Vandemoortele-RealChocolatedoonys_D80_44477-2.jpg', 'Chocolate glazed donut', 1.50, 18, CURRENT_TIMESTAMP),
    (54, 'Donut 9/10', 'https://images-polarfood-cdn.rshop.sk/lg/products/11513817b5820ef35ad129147e0d1096.jpg', '9/10 rating donut', 1.40, 15, CURRENT_TIMESTAMP),
    (55, 'Donut Homer', 'https://www.ifresh.sk/wp-content/uploads/2024/06/89742.jpg', 'Pink glazed donut', 1.30, 20, CURRENT_TIMESTAMP),
    (56, 'Donut Maracuja', 'https://images.cdn.europe-west1.gcp.commercetools.com/1df039f1-4705-4f79-aa90-cf907a6ec063/82-328973-1980098125-JE7icdqw-medium.jpg', 'Passionfruit donut', 1.60, 10, CURRENT_TIMESTAMP),
    (57, 'Donut Shrek', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcShjYfrMK96a089nIE-X5cd9ylZUJpsjW16rEFHkgENL-nesqnD-jFwF4iWF5l_A2A_re4&usqp=CAU', 'Green glazed donut', 1.50, 12, CURRENT_TIMESTAMP),
--     -- Pizzas
    (58, 'Pizza Ristorante Quattro Formaggi', 'https://lunys.sk/wp-content/uploads/2022/05/627493e1ae71b-300x300.jpg', 'Four cheese pizza', 4.50, 8, CURRENT_TIMESTAMP),
    (59, 'Pizza Ristorante Prosciutto', 'https://lunys.sk/wp-content/uploads/2022/05/6274963a07a66.jpg', 'Ham pizza', 4.30, 10, CURRENT_TIMESTAMP),
    (60, 'Pizza Ristorante Margherita', 'https://lunys.sk/wp-content/uploads/2022/09/10412190_T1.jpg', 'Classic Margherita pizza', 4.00, 12, CURRENT_TIMESTAMP),
    (61, 'Pizza Ristorante Pollo', 'https://www.oetker.sk/assets/hygraph/output=format:webp/resize=width:320/quality=value:75/compress/ZvT38NW4TRy1yP8dmcf9', 'Chicken pizza', 4.40, 10, CURRENT_TIMESTAMP),
    (62, 'Pizza Ristorante Salame', 'https://www.ifresh.sk/wp-content/uploads/2024/06/96940.jpg', 'Salami pizza', 4.60, 9, CURRENT_TIMESTAMP),
    (63, 'Pizza Guseppe Cheese', 'https://www.oetker.sk/assets/hygraph/output=format:webp/resize=width:320/quality=value:75/compress/REkHVsVMT7K5gypCQHIy', 'Cheese stuffed crust pizza', 4.70, 8, CURRENT_TIMESTAMP),
    (64, 'Pizza Guseppe Hawaii', 'https://www.oetker.sk/assets/hygraph/output=format:webp/resize=width:320/quality=value:75/compress/WfA8tpwbRnqic49wzbUl', 'Hawaiian pizza', 4.80, 7, CURRENT_TIMESTAMP),
    (65, 'Pizza Guseppe Mushroom', 'https://www.oetker.sk/assets/hygraph/output=format:webp/resize=width:320/quality=value:75/compress/QgHXTxwGRcq38uc9CiX9', 'Mushroom pizza', 4.70, 6, CURRENT_TIMESTAMP),
    (66, 'Pizza Guseppe Salami', 'https://www.oetker.sk/assets/hygraph/output=format:webp/resize=width:320/quality=value:75/compress/k7w0KrsiQaW1wTRpS7bE', 'Salami pizza', 4.60, 8, CURRENT_TIMESTAMP),
    (67, 'Pizza Guseppe Ham & Garlic', 'https://www.oetker.pl/assets/hygraph/output=format:webp/resize=width:320/quality=value:75/compress/iiH5A7SlaEaJZcX9UpxQ', 'Ham and garlic pizza', 4.80, 5, CURRENT_TIMESTAMP),
    (68, 'Pizza Guseppe Curry', 'https://www.oetker.sk/assets/hygraph/output=format:webp/resize=width:320/quality=value:75/compress/dEfPCRazTEOnECs0oSPF', 'Curry-flavored pizza', 5.00, 4, CURRENT_TIMESTAMP),
--     -- Noodles
    (69, 'Noodles Mendake', 'https://www.foodland.sk/sub/foodland.sk/shop/product/instantne-bezvajecne-rezance-mendake-mama-200-g-2303.jpg', 'Egg-free noodles', 1.20, 25, CURRENT_TIMESTAMP),
    (70, 'Noodles Hwa Ramyun', 'https://cdn.myshoptet.com/usr/www.chillimarket.sk/user/shop/big/956-1_956-2-instantne-rezance-hwa-ramyun-paldo-120g.jpg?65ba1abe', 'Spicy instant noodles', 1.50, 20, CURRENT_TIMESTAMP),
    (71, 'Noodles King Cook', 'https://www.foodland.sk/sub/foodland.sk/shop/product/instantne-rezance-king-cook-250-g-890.jpg', 'King Cook noodles', 1.10, 30, CURRENT_TIMESTAMP),
    (72, 'Noodles Maggi Teriyaki', 'https://cdn.myshoptet.com/usr/www.vsetkovyhodne.sk/user/shop/big/35165_maggi-asia-teriyaki-rezance-vyprazane-instantne-130-g.png?66906444', 'Teriyaki flavor noodles', 1.40, 22, CURRENT_TIMESTAMP),
    (73, 'Noodles Maggi Thai Curry', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQqUKb-a5fjRujkGPiFTBKDve3e1hXwU7IZrQ&s', 'Thai curry noodles', 1.50, 15, CURRENT_TIMESTAMP),
    (74, 'Noodles Maggi Duck', 'https://cdn.myshoptet.com/usr/www.vsetkovyhodne.sk/user/shop/big/35168_maggi-asia-duck-rezance-119-g.png?66906444', 'Duck-flavored noodles', 1.60, 18, CURRENT_TIMESTAMP),
    (75, 'Noodles Maggi Chicken', 'https://cdn.mymarket.gr/images/styles/original/16_09_24/863a1e3f-c7e6-4a96-8e05-f7c5f02a86ce_E1.jpg', 'Chicken-flavored noodles', 1.50, 20, CURRENT_TIMESTAMP),
    (76, 'Noodles Buldak Kimchi', 'https://cdn.myshoptet.com/usr/www.mocafino.sk/user/shop/big/24030-1_samyang-buldak-ramen-kimchi-nejkafe-cz.jpg?65e51f62', 'Kimchi noodles', 1.70, 10, CURRENT_TIMESTAMP),
    (77, 'Noodles Buldak Hot Chicken', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS0mrVV9awMGVmI8qsUITN6qaFdnciq7R10nM49Fjiv4_OtJxoYB_wnl_VERQss_0nzUlg&usqp=CAU', 'Spicy chicken noodles', 1.80, 8, CURRENT_TIMESTAMP),
    (78, 'Noodles Buldak Extra Hot', 'https://cdn.myshoptet.com/usr/www.nonari.sk/user/shop/big/23100_samyang-buldak-2x-extra-palive-kureci-nudle--140g-.jpg?65e5207b', 'Extra hot noodles', 2.00, 5, CURRENT_TIMESTAMP),
--     -- Rice
    (79, 'Rice Giana Round', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRFY47H970NDU9qz-Lg9OKBS40Xxnaxwh0JvQ&s', 'Round grain rice', 1.00, 30, CURRENT_TIMESTAMP),
    (80, 'Rice Giana Jasmine', 'https://digitalcontent.api.tesco.com/v2/media/ghs/2bacdc96-7b35-4e6c-9c37-4f0bade11d7a/f9bce9cb-13fd-4bb1-a62d-5fed51e145bb_65024013.jpeg?h=540&w=540', 'Jasmine rice', 1.50, 25, CURRENT_TIMESTAMP),
    (81, 'Rice Giana Parboiled', 'https://www.goral.sk/wcd/eshop/files/307/1586/images/thumb/r%C3%BD%C5%BEe_parboiled_Giana_PNP_0476_WEB.jpg', 'Parboiled rice', 1.40, 20, CURRENT_TIMESTAMP),
    (82, 'Rice Lagris Parboiled', 'https://imageproxy.wolt.com/assets/6676403db669ac6c8eaeb03c', 'Premium parboiled rice', 1.60, 18, CURRENT_TIMESTAMP),
    (83, 'Rice Lagris Arborio', 'https://imageproxy.wolt.com/assets/6676402b88898805c808c629', 'Arborio rice for risotto', 1.80, 15, CURRENT_TIMESTAMP),
    (84, 'Rice Lagris Round', 'https://imageproxy.wolt.com/assets/6676486fd8b6b903a55fe772', 'Round grain rice', 1.20, 25, CURRENT_TIMESTAMP),
    (85, 'Rice Sos Long', 'https://imageproxy.wolt.com/assets/66760499b669ac6c8eae9158', 'Long grain rice', 1.30, 22, CURRENT_TIMESTAMP),
    (86, 'Rice Sos Classic', 'https://digitalcontent.api.tesco.com/v2/media/ghs/bf88aac1-eb23-4064-8e4c-69c396dca718/9bc275fd-e8f8-4692-93a1-6a09a146dd96_1972611904.jpeg?h=540&w=540', 'Classic rice', 1.10, 28, CURRENT_TIMESTAMP),
    -- Jams
    (87, 'Jam Extra Strawberry', 'https://digitalcontent.api.tesco.com/v2/media/ghs/d6a08413-3e20-488d-ade9-6fbcce33533a/12a55167-bfe2-432a-96bc-188f5113abe0_698827527.jpeg?h=540&w=540', 'Strawberry jam', 2.00, 20, CURRENT_TIMESTAMP),
    (88, 'Jam Extra Apricot', 'https://lunys.sk/wp-content/uploads/2024/04/660fb68a7d537.jpg', 'Apricot jam', 2.10, 18, CURRENT_TIMESTAMP),
    (89, 'Jam Extra Raspberry', 'https://lunys.sk/wp-content/uploads/2024/04/660fb1da6d2ea.jpg', 'Raspberry jam', 2.20, 20, CURRENT_TIMESTAMP),
    (90, 'Jam Extra Pineapple', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSe_IN4k50xDvNL78oNk09QETYkpKtBR18M8Q&s', 'Pineapple jam', 2.30, 12, CURRENT_TIMESTAMP),
    (91, 'Jam Extra Zero Sugar', 'https://media.cdn.kaufland.de/product-images/1024x1024/2ff6d56116125eedac03a02de5f6bf36.jpg', 'Sugar-free jam', 2.50, 10, CURRENT_TIMESTAMP),
    (92, 'Jam Mixed Fruit', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTLIOq4RfQD6JSripqzqdi3TY3Jp8IW_HITMg&s', 'Mixed fruit jam', 2.00, 18, CURRENT_TIMESTAMP),
    (93, 'Jam Malas Strawberry Zero', 'https://cdn.dotpe.in/longtail/store-items/7168353/53lEOe0F.jpeg', 'Strawberry zero sugar jam', 2.40, 15, CURRENT_TIMESTAMP),
    (94, 'Jam Malas Strawberry', 'https://www.malasfruit.com/public/images/uploads/products/1664211101_Strawberry%20Jam%20Seedless%20500g%20B.png', 'Seedless strawberry jam', 2.50, 18, CURRENT_TIMESTAMP),
    (95, 'Jam Malas Mix', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVL4wtlq0IV19Gan2ZVQy3vlLjxO4KPAP_VEBLZjJiirvtbDZifUqjf3Ol2UaQ71pcc0E&usqp=CAU', 'Mixed fruit jam', 2.20, 15, CURRENT_TIMESTAMP),
    (96, 'Jam Malas Mango', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQWDHXAJh-OJihFItG9lbc9zyZ4wWFhdhilQAFpXEN7C7p-jpN-Ac8OxrdggURoKtbkkhQ&usqp=CAU', 'Mango jam', 2.40, 12, CURRENT_TIMESTAMP),
--     Oats
    (97, 'Oats Strawberry', 'https://www.bonavita.sk/data/bonavita.sk/images/tmp/dobra_kasa_ovsena_jahoda_55g_cena_za_1_karton_20_kusov__b_469859e20660a5f1.jpg', 'Strawberry flavored oats', 1.10, 25, CURRENT_TIMESTAMP),
    (98, 'Oats Chocolate', 'https://www.bonavita.sk/images/dobra_kasa_ovsena_s_cokoladou_55g_cena_za_1_karton_20_kusov__4ab6b6bd7e60337f@2x.jpg', 'Chocolate flavored oats', 1.20, 22, CURRENT_TIMESTAMP),
    (99, 'Oats Fruit', 'https://www.bonavita.sk/images/dobra_kasa_ovsena_lesne_ovocie_55g_cena_za_1_karton_20_kusov__34bde6924d37f11a@2x.jpg', 'Mixed fruit flavored oats', 1.30, 20, CURRENT_TIMESTAMP),
    (100, 'Oats Almond', 'https://img-cdn.heureka.group/v1/a9512ebb-e45d-4940-b7e0-f3533b1dd045.jpg?width=400&height=400', 'Almond flavored oats', 1.40, 18, CURRENT_TIMESTAMP),
    (101, 'Oats Apple', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRSV7ruQ2a3V4eIVWqNq0AaTaXEi3cgVGHvv2fOPbzt2bmSkEJfjPRJ-8hMYwdC9_-T5yk&usqp=CAU', 'Apple flavored oats', 1.30, 22, CURRENT_TIMESTAMP),
    (102, 'Oats Raspberry', 'https://img-cdn.heureka.group/v1/37df2f58-ebcd-4475-911c-85a630ea4d7e.jpg?width=350&height=350', 'Raspberry flavored oats', 1.35, 20, CURRENT_TIMESTAMP),
    (103, 'Oats Oetker Apple', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSa8aXE6DV5hcKE9Kwmu7ibk7JM8L9iDxGP2SKBR7oQkFjRiM94d6qrMPwVndgQVNjGdrE&usqp=CAU', 'Dr. Oetker apple oats', 1.40, 18, CURRENT_TIMESTAMP),
    (104, 'Oats Oetker Banana', 'https://www.tapnovinky.sk/wp-content/uploads/2019/10/dr_oetker_ovesna_kase_banan_chia_3d_rgb.jpg', 'Dr. Oetker banana oats', 1.50, 15, CURRENT_TIMESTAMP),
    (105, 'Oats Oetker Vanilla', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT50MRAcbUDySS6N7hDgvtDclyRcHyubeiCSNBi-Dz5P4W2gPfZZywQIdsPkM6e3NmAap4&usqp=CAU', 'Dr. Oetker vanilla oats', 1.50, 12, CURRENT_TIMESTAMP),
    (106, 'Oats Oetker Cranberry', 'https://online.idea.rs/images/products/401/401105147_1l.jpg?1677672937', 'Dr. Oetker cranberry oats', 1.45, 10, CURRENT_TIMESTAMP);

INSERT INTO "sales" (id, product_id, sale_price, date_from, date_to, created_at)
VALUES
    (1, 1, 0.89, '2024-11-01 00:00:00', '2024-12-10 23:59:59', CURRENT_TIMESTAMP), -- Yogurt
    (2, 8, 1.20, '2024-11-10 00:00:00', '2024-11-20 23:59:59', CURRENT_TIMESTAMP), -- Pasta
    (3, 13, 0.75, '2024-11-15 00:00:00', '2024-11-25 23:59:59', CURRENT_TIMESTAMP), -- Milk
    (4, 23, 1.10, '2024-12-01 00:00:00', '2024-12-15 23:59:59', CURRENT_TIMESTAMP), -- Crisps
    (5, 28, 0.60, '2024-12-05 00:00:00', '2024-12-12 23:59:59', CURRENT_TIMESTAMP), -- Bubblegum
    (6, 33, 4.00, '2024-11-20 00:00:00', '2024-11-30 23:59:59', CURRENT_TIMESTAMP), -- Coffeea
    (7, 43, 0.70, '2024-11-25 00:00:00', '2024-12-05 23:59:59', CURRENT_TIMESTAMP), -- Snack
    (8, 53, 1.20, '2024-11-10 00:00:00', '2024-11-15 23:59:59', CURRENT_TIMESTAMP), -- Donut
    (9, 60, 3.50, '2024-11-01 00:00:00', '2024-11-10 23:59:59', CURRENT_TIMESTAMP), -- Pizza
    (10, 69, 1.00, '2024-11-20 00:00:00', '2024-12-01 23:59:59', CURRENT_TIMESTAMP), -- Noodles
    (11, 79, 0.90, '2024-11-15 00:00:00', '2024-11-25 23:59:59', CURRENT_TIMESTAMP), -- Rice
    (12, 87, 1.80, '2024-11-01 00:00:00', '2024-11-15 23:59:59', CURRENT_TIMESTAMP), -- Jam
    (13, 97, 0.90, '2024-11-10 00:00:00', '2024-11-20 23:59:59', CURRENT_TIMESTAMP), -- Oats
    (14, 2, 0.45, '2024-12-05 00:00:00', '2024-12-15 23:59:59', CURRENT_TIMESTAMP), -- Yogurt Sophie
    (15, 9, 1.00, '2024-11-10 00:00:00', '2024-11-20 23:59:59', CURRENT_TIMESTAMP), -- Pasta Morton Penne
    (16, 15, 1.10, '2024-12-01 00:00:00', '2024-12-10 23:59:59', CURRENT_TIMESTAMP), -- Milk Coconut
    (17, 25, 1.10, '2024-11-15 00:00:00', '2024-11-25 23:59:59', CURRENT_TIMESTAMP), -- Chips Lays Spicy
    (18, 30, 0.55, '2024-12-01 00:00:00', '2024-12-12 23:59:59', CURRENT_TIMESTAMP), -- Bubblegum Spearmint
    (19, 40, 3.90, '2024-11-20 00:00:00', '2024-11-30 23:59:59', CURRENT_TIMESTAMP), -- Coffee Aiello Classico
    (20, 50, 0.75, '2024-11-25 00:00:00', '2024-12-05 23:59:59', CURRENT_TIMESTAMP) -- Snack Knoppers Coconut

INSERT INTO "deliveries" ("id", "name", "price", "created_at")
VALUES
    (1, 'DPD delivery', 1.20, NOW()),
    (2, 'SPS delivery', 3.80, NOW()),
    (3, 'DHL delivery', 4.90, NOW());
