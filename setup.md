# once everything is running, initialize the system from CLI

export BASEURL="http://192.168.99.100:8080"

curl -u superuser:password -X PUT $BASEURL/system/database/setup
curl -u superuser:password -X PUT $BASEURL/system/database/bootstrap
curl -u superuser:password -X GET $BASEURL/system/superuser/setup    

# you'll find these by logging into portal as test-organization owner and creating a new organization
export ORGID="50da3c74-2baf-11e7-9f08-024209954cb0"
export APPID="5238ba5f-2baf-11e7-9f08-024209954cb0"
export CLIENTID="b3U6UNo8dCuvEeefCAJCCZVMsA"
export CLIENTSECRET="b3U6MJnuMAkvrpwsX2u3DAe3EOJo6c0"

# add roles

curl -X POST "$BASEURL/$ORGID/$APPID/roles?client_id=$CLIENTID&client_secret=$CLIENTSECRET" -d '{"name":"volunteer"}'
curl -X POST "$BASEURL/$ORGID/$APPID/roles?client_id=$CLIENTID&client_secret=$CLIENTSECRET" -d '{"name":"campaign"}'

# add users

curl -X POST "$BASEURL/$ORGID/$APPID/users?client_id=$CLIENTID&client_secret=$CLIENTSECRET" -d '{"username":"sblackmon", "email": "steve@blackmon.org", "name": "Steve Blackmon", "password": "steveb"}'

# assign roles

curl -X POST "$BASEURL/$ORGID/$APPID/roles/volunteer/users/sblackmon?client_id=$CLIENTID&client_secret=$CLIENTSECRET"

# misc
