# Capitole Challenge Price API  

>Java Challenge


## Github's project

- [view project](https://github.com/users/raul-varela/projects/1)



## H2
```
BASE_URL: http://localhost:8082
```

{BASE_URL}/price-list/h2/h2-console/

## Swagger

```
BASE_URL: http://localhost:8082
```

{BASE_URL}/price-list/ui-swagger/index.html

## Postman

https://www.getpostman.com/collections/0e94422445bdeead8c08

## Curl

## API Reference

```
BASE_URL: http://localhost:8082
```

#### Signup -  New User

```http
  curl --location --request POST '{BASE_URL}/price-list/api/auth/signup' \
--header 'Content-Type: application/json' \
--data-raw '{
"username": "capitole",
"password": "123123",
"email": "capitole@server.com",
"role": [
"admin"
]
}'
```

| Parameter | Type       | Description                |
| :-------- |:-----------| :------------------------- |
| `username` | `string`   | **Required**.  |
| `password` | `string`   | **Required**.  |
| `email` | `string`   | **Required**.  |
| `role` | `string[]` | **Required**.  |



#### Signin - Created User

```http
curl --location --request POST '{BASE_URL}/price-list/api/auth/signin' \
--header 'Content-Type: application/json' \
--data-raw '{
"username": "capitole",
"password": "123123"
}'
```

| Parameter | Type       | Description                |
| :-------- |:-----------| :------------------------- |
| `username` | `string`   | **Required**.  |
| `password` | `string`   | **Required**.  |


#### Access Admin Menu

```http
curl --location --request GET '{BASE_URL}/price-list/api/test/admin' \
--header 'Authorization: Bearer {access_token}'
```

| Header     | Type       | Description                |
|:-----------|:-----------| :------------------------- |
| `Authorization` | `string`   | **Required**.  |


#### Find Price By Brand & Product & Date

```http
curl --location --request POST '{BASE_URL}/price-list/api/v1/price/getPriceByProductAndDate' \
--header 'Authorization: Bearer {access_token}' \
--header 'Content-Type: application/json' \
--data-raw '{
"brandId": "1",
"productId": "1",
"applicationTime": "2020-10-02 22:40:11"
}'
```
| Header     | Type       | Description                |
|:-----------|:-----------| :------------------------- |
| `Authorization` | `string`   | **Required**.  |