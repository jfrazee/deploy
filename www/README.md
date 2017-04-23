docker run -d --name=usergrid-www --net=host -p 10000:10000 bluesquad/www

# initialize databases

export BASEURL="http://192.168.99.100:8080"

curl -u superuser:password -X PUT $BASEURL/system/database/setup
curl -u superuser:password -X PUT $BASEURL/system/database/bootstrap
curl -u superuser:password -X GET $BASEURL/system/superuser/setup    

export ORGID="4d4a3c70-2843-11e7-84bf-02420e5c3ca7"
export APPID="7951e7d6-2843-11e7-84bf-02420e5c3ca7"

# add roles

curl  -u superuser:password -X POST $BASEURL/$ORGID/$APPID/roles -d '{"name":"volunteer"}'
curl  -u superuser:password -X POST $BASEURL/$ORGID/$APPID/roles -d '{"name":"campaign"}'
