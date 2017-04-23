# once everything is running, initialize the system from CLI

export BASEURL="http://192.168.99.100:8080"

curl -u superuser:password -X PUT $BASEURL/system/database/setup
curl -u superuser:password -X PUT $BASEURL/system/database/bootstrap
curl -u superuser:password -X GET $BASEURL/system/superuser/setup    

# you'll find these by logging into portal as test-organization owner and creating a new organization
export ORGID="4d4a3c70-2843-11e7-84bf-02420e5c3ca7"
export APPID="7951e7d6-2843-11e7-84bf-02420e5c3ca7"
export CLIENTID=""
export CLIENTSECRET=""

# add roles

curl -X POST "$BASEURL/$ORGID/$APPID/roles?client_id=$CLIENTID&client_secret=$CLIENTSECRET" -d '{"name":"volunteer"}'
curl -X POST "$BASEURL/$ORGID/$APPID/roles?client_id=$CLIENTID&client_secret=$CLIENTSECRET" -d '{"name":"campaign"}'

# add users

curl -X POST "$BASEURL/$ORGID/$APPID/users?client_id=$CLIENTID&client_secret=$CLIENTSECRET" -d '{"username":"sblackmon", "email": "steve@blackmon.org", "name": "Steve Blackmon", "password": "steveb"}'

# assign roles

curl -X POST "$BASEURL/$ORGID/$APPID/roles/volunteer/users/sblackmon?client_id=$CLIENTID&client_secret=$CLIENTSECRET"

# misc
