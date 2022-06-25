### Tasks
***
1. Create a 10 seconds interval scheduler to retrieve the pricing from the source above and store the best pricing into the database.

2. Create an api to retrieve the latest best aggregated price.
```
/api/price
/api/price/{symbol}
```
3. Create an api which allows users to trade based on the latest best aggregated
price.
Remarks: Do not integrate with other third party system

```
/api/trade
```
**POST Request Example**
```
{
    "userId": 1,
    "symbol": "ETHUSDT",
    "transType": "BUY",
    "amount": 500
}
```

4. Create an api to retrieve the user's crypto currencies wallet balance.

```
/api/user/{user_id}/wallet
```

5. Create an api to retrieve the user trading history.

```
/api/user/{user_id}/history
```
