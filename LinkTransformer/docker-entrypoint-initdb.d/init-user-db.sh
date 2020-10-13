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

    INSERT INTO WEB_DEEP_CONVERSION VALUES ('boutiqueId', 'CampaignId');
    INSERT INTO WEB_DEEP_CONVERSION VALUES ('merchantId', 'MerchantId');
    INSERT INTO WEB_DEEP_CONVERSION VALUES ('-p-', 'Product&ContentId');
    INSERT INTO WEB_DEEP_CONVERSION VALUES ('q', 'Search&Query');

    INSERT INTO PATH_LOOKUP VALUES ('baseWebLink', 'https://www.trendyol.com');
    INSERT INTO PATH_LOOKUP VALUES ('homeWebLink', 'https://www.trendyol.com');
    INSERT INTO PATH_LOOKUP VALUES ('baseDeepLink', 'ty://?Page=');
    INSERT INTO PATH_LOOKUP VALUES ('homeDeepLink', 'ty://?Page=Home');

    INSERT INTO PATH_LOOKUP VALUES ('validURLPath', '/{BrandName-or-CategoryName}/{ProductName}-p-{ContentId}');
    INSERT INTO PATH_LOOKUP VALUES ('validURLPath', '/tum--urunler');
    INSERT INTO PATH_LOOKUP VALUES ('validDeepPath', 'Product&ContentId');
    INSERT INTO PATH_LOOKUP VALUES ('validDeepPath', 'Search&Query');

EOSQL