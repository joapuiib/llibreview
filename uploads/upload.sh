#!/bin/bash
USER=ec2-user
HOST="<host>"
FILES_PATH="/var/app/uploads"

COMMAND="scp -r -i labsuser.pem llibre/ $USER@$HOST:$FILES_PATH"
echo $COMMAND
$COMMAND

COMMAND="scp -r -i labsuser.pem autor/ $USER@$HOST:$FILES_PATH"
echo $COMMAND
$COMMAND
