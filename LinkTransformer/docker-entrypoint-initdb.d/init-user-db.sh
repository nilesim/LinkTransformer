#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL

    CREATE TABLE WEB_DEEP_CONVERSION (
      web_key VARCHAR ( 200 ),
      deep_key VARCHAR ( 200 )
    );

    CREATE TABLE PATH_LOOKUP (
      PATH_KEY VARCHAR ( 200 ),
      PATH_VALUE VARCHAR ( 200 )
    );

    CREATE TABLE ENDPOINT_LOGS (
      id SERIAL,
      REQUEST VARCHAR ( 256 ),
      RESPONSE VARCHAR ( 256 )
    );

    INSERT INTO WEB_DEEP_CONVERSION VALUES ('boutiqueId', 'CampaignId');
    INSERT INTO WEB_DEEP_CONVERSION VALUES ('merchantId', 'MerchantId');
    INSERT INTO WEB_DEEP_CONVERSION VALUES ('-p-', 'Product&ContentId');
    INSERT INTO WEB_DEEP_CONVERSION VALUES ('q', 'Search&Query');
    INSERT INTO WEB_DEEP_CONVERSION VALUES ('/tum--urunler', '');
    INSERT INTO WEB_DEEP_CONVERSION VALUES ('/{brand}/{name}-p-{ContentId}',
                                            'Product&ContentId={ContentId}');

    INSERT INTO PATH_LOOKUP VALUES (1,'baseWebLink', 'https://www.trendyol.com');
    INSERT INTO PATH_LOOKUP VALUES (2,'homeWebLink', 'https://www.trendyol.com');
    INSERT INTO PATH_LOOKUP VALUES (3,'baseDeepLink', 'ty://?Page=');
    INSERT INTO PATH_LOOKUP VALUES (4,'homeDeepLink', 'ty://?Page=Home');

    INSERT INTO PATH_LOOKUP VALUES (5, 'validURLPath', '/{brand}/{name}-p-{ContentId}');
    INSERT INTO PATH_LOOKUP VALUES (6, 'validURLPath', '/tum--urunler');
    INSERT INTO PATH_LOOKUP VALUES (7, 'validDeepPath', 'Product&ContentId');
    INSERT INTO PATH_LOOKUP VALUES (8, 'validDeepPath', 'Search&Query');


EOSQL