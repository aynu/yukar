# DB�T�[�o

## ���[�U

### �Ǘ����[�U�ւ̐ؑ�
`> set PGUSER=postgres`

### ���[�U�̍쐬
`> createuser --createdb --encrypted --inherit --login --pwprompt --createrole --superuser --replication yukar`

### ���[�U�̍폜
`> dropuser yukar`

## DB

### DB�̍쐬
`> createdb --encoding=UTF8 --owner=yukar --template=template0 yukar "Aynu Yukar Database"`

### DB�̍폜
`> dropdb yukar`

# JAAS

## �F�؃e�[�u���̍쐬
`yukar=# create table credential (username varchar(128), password varchar(128), userrole varchar(128));`

## �F�؃f�[�^�̍쐬
`yukar=# insert into credential (username, password, userrole) values ('', '', '');`
