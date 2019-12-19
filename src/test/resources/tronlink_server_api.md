# Chrome
1.chrome后台公告相关接口<br>
POST   /api/activity/announcement/increase                    handler.AddAnnouncementHandler
POST   /api/activity/announcement/update                      handler.UpdateAnnouncementHandler
DELETE /api/activity/announcement/delete/:id                  handler.DeleteAnnouncementHandler
GET    /api/activity/announcement/list                        handler.QueryAnnouncementListHandler

2.chrome公告相关接口<br>
GET    /api/activity/announcement/reveal_v2                   handler.QueryAnnouncementHandler
POST   /api/activity/announcement/pv                          handler.UpdateAnnouncementPvHandler

3.chrome网站黑名单查询<br>
GET    /api/activity/website/blacklist                        handler.QueryWebsiteBlacklistHandler

4.chrome后台网站黑名单接口<br>
POST   /api/activity/website/add                              handler.InsertWebsiteBlacklistHandler
POST   /api/activity/website/update                           handler.UpdateWebsiteInfoHandler
POST   /api/activity/website/delete                           handler.DeleteWebsiteHandler
GET    /api/activity/website/list                             handler.GetWebsiteBlacklistHandler

5.chrome钱包获取trc20余额<br>
GET    /api/wallet/trc20_info                                 handler.QueryTrc20InfoHanlder

6.chrome ieo
GET    /api/wallet/ieo                                        handler.IeoListHandler

7.chrome 官方token
GET    /api/wallet/official_token                             handler.QueryOfficialTokenHandler

8.chrome钱包获取trc20余额
GET    /api/wallet/trc20_info                                 handler.QueryTrc20InfoHanlder

                 

# GET /api/v1/wallet/feedback  
## Api description
提交用户反馈 

handler.InsertUserFeedbackHandler
## URL Parameter 说明
| key | Requried | type | description |
|-----|:--------:|------|-------------|
| system | n | string | 系统版本（Android/iOS） |
| title | n | string | 标题 |
| depict | n | string | 内容 |
| email | n | string | 邮箱地址（有校验） |

## Response Json说明
| key | Requried | type | description |
|-----|----------|------|-------------|
| code  | y  | int | 返回状态码 为0表示操作成功，其他值参见message |
| msg | y | string | 提示信息 |
## Response sample
```json
{

    "msg":"success",
    "code":0

}
```

# GET /api/v1/wallet/startup

## Api description

启动图

handler.StartUpPageHandler

## URL Parameter 说明

| key | Requried | type | description |
|-----|:--------:|------|-------------|

## Response Json说明

| key | Requried | type | description |
|-----|----------|------|-------------|
| code  | y  | int | 返回状态码 为0表示操作成功，其他值参见message  |
| msg | y | string | 提示信息 |
| data | y | dict | 数据信息 |

## data说明
| key | Requried | type | description |
|-----|----------|------|-------------|
| startTime | y | int | 开始时间 |
| endTime | y | int | 结束时间 |
| rewardStatus | y | int | 抽奖活动状态0-未开始， 1-进行中， 2-已结束 |
| airdropStatus | y | int | 空投活动状态0-未开始， 1-进行中， 2-已结束 |


## Response sample

```json
{
    "code": 0,
    "message": "OK",
    "data": {
        "startTime": 1571760000,
        "endTime": 1572019200,
        "rewardStatus": 1,
        "airdropStatus": 1
    }
}

```

# GET /api/v1/wallet/community

## Api description

加入社群信息

handler.CommunityHandler

## URL Parameter 说明

| key | Requried | type | description |
|-----|:--------:|------|-------------|

## Response Json说明

| key | Requried | type | description |
|-----|----------|------|-------------|
| code  | y  | int | 返回状态码 为0表示操作成功，其他值参见message  |
| msg | y | string | 提示信息 |
| data | y | dict | 数据信息 |

## data说明
| key | Requried | type | description |
|-----|----------|------|-------------|
| twitter | y | string | 推特地址 |
| WeChat | y | string | 微信公众号名称 |
| telegram_en | y | string | telegram英文地址 |
| telegram_cn | y | string | telegram中文地址 |


## Response sample

```json
{
    "code": 0,
    "msg": "success",
    "data": {
                "telegram_en": "https://t.me/TronLink",
                "telegram_cn": "https://t.me/TronLinkCN",
                "twitter": "https://twitter.com/TronLinkWallet",
                "WeChat": "tronlink"
            }
}

```

# GET    /api/v1/wallet/upgrade

## Api description

用户更新

## URL Header 说明

| key | Requried | type | description |
|-----|:--------:|------|-------------|
| system | y | string | 系统版本（Android/iOS） |
| Version | y | string | 客户端版本 |
| Lang | y | string | 语言 |
| DeviceID | y | string | 设备id |
| DownloadPlatform | y | string | 下载渠道 |
| packageName | y | string | 包名 |




## URL Parameter 说明

| key | Requried | type | description |
|-----|:--------:|------|-------------|

## Response Json说明

| key | Requried | type | description |
|-----|----------|------|-------------|
| code  | y  | int | 返回状态码 为0表示操作成功，其他值参见message  |
| msg | y | string | 提示信息 |
| data | y | dict | 数据信息 |
| upgrade | y | bool | 是否需要更新 |
| latest_version | y | bool | 是否为最新版本 |
| force | y | bool |  |
| jump_google_play | y | bool |  |




## data说明
| key | Requried | type | description |
|-----|----------|------|-------------|
| internal_update_url | y | string | apk下载url |
| google_apk_url | y | string | apk下载url |
| title | y | string | 标题 |
| strategy | y | string | 描述 |
| version | y | string | 版本号 |
| url | y | string | url |





## Response sample

```json
{
    "msg":"success",
    "code":0,
    "data":{
        "internal_update_url":"https://www.tronlink.org/download/android_TronLink/TronLink3.2.5.apk",
        "google_apk_url":"https://www.tronlink.org/download/googleplay_TronLink/tronlink_googleplay_v3.2.5.apk",
        "title":"Discover new version V3.2.5",
        "strategy":"Notice: TronLink has upgraded to TronLink Pro, voting reward feature has been added into the new version, you can claim your rewards with one click.\nUpdate method: Search for 'TronLink Pro' in Google Play and complete installation. You can submit a ticket in 'Help Center' for help.",
        "version":"3.2.5",
        "url":"https://tronlink.org/"
        },
    "upgrade":false,
    "latest_version":false,
    "force":false,
    "jump_google_play":false
}

```

# GET /api/v1/wallet/version_log

## Api description

更新日志列表

handler.WalletVersionLogHandler

## URL Parameter 说明

| key | Requried | type | description |
|-----|:--------:|------|-------------|
| system | y | string | 系统版本（Android/iOS） |
| lang | y | string | 语言 |


## Response Json说明

| key | Requried | type | description |
|-----|----------|------|-------------|
| code  | y  | int | 返回状态码 为0表示操作成功，其他值参见message  |
| msg | y | string | 提示信息 |
| data | y | dict | 数据信息 |

## data说明
| key | Requried | type | description |
|-----|----------|------|-------------|
| system | y | string | 系统版本 |
| create_time | y | string | 时间 |
| title | y | string | 标题 |
| strategy | y | string | 内容 |
| version | y | string | 版本号 |



## Response sample

```json
{
"code": 0,
"msg": "success",
"data": [
    {
        "system": "Android",
        "version": "3.2.5",
        "title": "Discover new version V3.2.5",
        "strategy": "1. Fixed bugs to improve product stability\n",
        "create_time": "Mon, 18 Nov 2019 08:50:05 GMT"
    }
],
"page_index": 1,
"pages": 2
}

```

# GET /api/v1/wallet/version_log

## Api description

更新日志列表

handler.WalletVersionLogHandler

## URL Parameter 说明

| key | Requried | type | description |
|-----|:--------:|------|-------------|
| system | y | string | 系统版本（Android/iOS） |
| lang | y | string | 语言 |


## Response Json说明

| key | Requried | type | description |
|-----|----------|------|-------------|
| code  | y  | int | 返回状态码 为0表示操作成功，其他值参见message  |
| msg | y | string | 提示信息 |
| data | y | dict | 数据信息 |

## data说明
| key | Requried | type | description |
|-----|----------|------|-------------|
| system | y | string | 系统版本 |
| create_time | y | string | 时间 |
| title | y | string | 标题 |
| strategy | y | string | 内容 |
| version | y | string | 版本号 |



## Response sample

```json
{
"code": 0,
"msg": "success",
"data": [
    {
        "system": "Android",
        "version": "3.2.5",
        "title": "Discover new version V3.2.5",
        "strategy": "1. Fixed bugs to improve product stability\n",
        "create_time": "Mon, 18 Nov 2019 08:50:05 GMT"
    }
],
"page_index": 1,
"pages": 2
}

```

# GET /api/v1/wallet/getLatestAPK

## Api description

获取最新版本

handler.GetLatestAPKUrl

## URL Parameter 说明

| key | Requried | type | description |
|-----|:--------:|------|-------------|


## Response Json说明

| key | Requried | type | description |
|-----|----------|------|-------------|
| code  | y  | int | 返回状态码 为0表示操作成功，其他值参见message  |
| msg | y | string | 提示信息 |
| data | y | string | 最新版本下载地址 |


## Response sample

```json
{
    "code":0,
    "data":"https://www.tronlink.org/download/android_TronLink/TronLink3.3.1.apk",
    "message":"OK"
}
```
# POST  /api/wallet/addasset

## Api description

添加资产

## URL Parameter 说明

| key | Requried | type | description |
|-----|:--------:|------|-------------|
| address | y | string | 地址(只传输地址，会返回已添加的资产) |
| token10 | n | string[] | 添加trc10资产 |
| token20 | n | string[] | 添加trc20资产 |
| token10Cancel | n | string[] | 删除trc10资产 |
| token20Cancel | n | string[] | 删除trc20资产 |



## Response Json说明

| key | Requried | type | description |
|-----|----------|------|-------------| 
| code| y    | number | 返回状态码 为0表示操作成功，其他值参见msg |
| massage | y | string | 提示信息 |
| data | y | list | data信息 |

## data说明
| key | Requried | type | description |
|-----|----------|------|-------------| 
| marktypeetId | n | int | 资产类型 |
| top | n | int |  |
| isOfficial | n | int |  |
| name | n | string | 名称 |
| shortName | n | string | 简称 |
| id | n | string |  |
| contractAddress | y | string | 合约地址 |
| balance | n | float64 | 余额 |
| totalBalance | n | float64 |  |
| logoUrl | n | string | logo图片地址 |
| precision | n | int32 | 精度 |
| marketId | n | int | 交易对id |
| price | n | float64 | 对trx价格 |
| trxCount | n | float64 |  |
| inMainChain | n | bool | 是否从主链映射过来的 |
| inSideChain | n | bool | 是否映射到侧链 |



## sample
```json
{
    "code": 0,
    "message": "OK",
    "data": [
        {
            "type": 2,
            "top": 1,
            "isOfficial": 0,
            "name": "Tether USD",
            "shortName": "USDT",
            "id": "",
            "contractAddress": "TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t",
            "balance": 0,
            "totalBalance": 0,
            "logoUrl": "https://coin.top/production/logo/TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t.png",
            "precision": 6,
            "marketId": 0,
            "price": 80.637742,
            "trxCount": 0,
            "inMainChain": false,
            "inSideChain": false
        }
    ]
}
```

# POST  /api/wallet/class/allasset

## Api description

获取添加和未添加的token

## URL Parameter 说明

| key | Requried | type | description |
|-----|:--------:|------|-------------|
| address | y | string | 地址 |



## Response Json说明

| key | Requried | type | description |
|-----|----------|------|-------------| 
| code| y    | number | 返回状态码 为0表示操作成功，其他值参见msg |
| massage | y | string | 提示信息 |
| data | y | list | data信息 |

## data说明
| key | Requried | type | description |
|-----|----------|------|-------------| 
| marktypeetId | n | int | 资产类型 |
| top | n | int |  |
| isOfficial | n | int |  |
| name | n | string | 名称 |
| shortName | n | string | 简称 |
| id | n | string |  |
| contractAddress | y | string | 合约地址 |
| balance | n | float64 | 余额 |
| totalBalance | n | float64 |  |
| logoUrl | n | string | logo图片地址 |
| precision | n | int32 | 精度 |
| marketId | n | int | 交易对id |
| price | n | float64 | 对trx价格 |
| trxCount | n | float64 |  |
| inMainChain | n | bool | 是否从主链映射过来的 |
| inSideChain | n | bool | 是否映射到侧链 |



## sample
```json
{
    "code": 0,
    "message": "OK",
    "data": [
        {
            "type": 1,
            "top": 0,
            "isOfficial": 0,
            "name": "TWLT",
            "shortName": "",
            "id": "1001580",
            "contractAddress": "",
            "balance": 0,
            "totalBalance": 0,
            "logoUrl": "",
            "precision": 0,
            "marketId": 0,
            "price": 0,
            "trxCount": 0,
            "inMainChain": false,
            "inSideChain": false
        }
    ]
}
```


# POST  /api/wallet/hot_token

## Api description

热门token

## URL Parameter 说明

| key | Requried | type | description |
|-----|:--------:|------|-------------|
| address | y | string | 地址 |



## Response Json说明

| key | Requried | type | description |
|-----|----------|------|-------------| 
| code| y    | number | 返回状态码 为0表示操作成功，其他值参见msg |
| massage | y | string | 提示信息 |
| data | y | list | data信息 |

## data说明
| key | Requried | type | description |
|-----|----------|------|-------------| 
| marktypeetId | n | int | 资产类型 |
| top | n | int |  |
| isOfficial | n | int |  |
| name | n | string | 名称 |
| shortName | n | string | 简称 |
| id | n | string |  |
| contractAddress | y | string | 合约地址 |
| balance | n | float64 | 余额 |
| totalBalance | n | float64 |  |
| logoUrl | n | string | logo图片地址 |
| precision | n | int32 | 精度 |
| marketId | n | int | 交易对id |
| price | n | float64 | 对trx价格 |
| trxCount | n | float64 |  |
| inMainChain | n | bool | 是否从主链映射过来的 |
| inSideChain | n | bool | 是否映射到侧链 |



## sample
```json
{
    "code": 0,
    "message": "OK",
    "data": {
        "totalTRX": 0,
        "price": {
            "priceCny": "0.0",
            "priceUSD": "0.0"
        },
        "token": [
            {
                "type": 1,
                "top": 1,
                "isOfficial": 1,
                "name": "BitTorrent",
                "shortName": "BTT",
                "id": "1002000",
                "contractAddress": "",
                "maincontractAddress": "",
                "balance": 0,
                "totalBalance": 0,
                "trxCount": 0,
                "logoUrl": "https://coin.top/production/logo/1002000.png",
                "precision": 6,
                "homePage": "www.bittorrent.com",
                "issueAddress": "",
                "tokenDesc": "Official Token of BitTorrent Protocol",
                "issueTime": "",
                "totalSupply": 990000000000000000,
                "price": 0.02101,
                "marketId": 39,
                "isInAssets": false,
                "time": 0,
                "ieoUrl": "",
                "ieoUrlZh": "",
                "yesterdayEarnings": 0,
                "totalEarnings": 0,
                "inActivity": false,
                "inMainChain": true,
                "inSideChain": true
            }
        ]
    }
}
```






# 已有文档

# POST  /api/wallet/assetlist

## Api description

版本 v1.0

assetlist接口

handler.GetAllAsset

## URL Parameter 说明

| key | Requried | type | description |
|-----|:--------:|------|-------------|
| address | y | string | 地址 |

## Response Json说明

| key | Requried | type | description |
|-----|----------|------|-------------| 
| code| y    | number | 返回状态码 为0表示操作成功，其他值参见msg |
| massage | y | string | 提示信息 |
| data | y | list | data信息 |

## data新加字段说明
| key | Requried | type | description |
|-----|----------|------|-------------| 
| marketId | y | int | trxmarket id |
| isInAssets | y | bool | 是否在个人资产中，hot token接口专用 |
| time | y | int | ieo剩余时间| 
| ieoUrl | n | string | ieo 链接 |
| ieoUrlZh | n | string | ieo 中文链接 |
| yesterdayEarnings | n | int | 昨日收益，usdt空投字段|
| totalEarnings | n | int | 总收益，usdt空投字段|



## sample
```json
{
    
}
```

# GET  /api/wallet/invite/leaderBoard

## Api description

版本 v1.0

积分排行榜接口

## URL Parameter 说明

| key | Requried | type | description |
|-----|:--------:|------|-------------|
| start | n | int | 查询起始位置 |
| limit | n | int | 查询条数 |

## Response Json说明

| key | Requried | type | description |
|-----|----------|------|-------------| 
| code| y    | number | 返回状态码 为0表示操作成功，其他值参见msg |
| massage | y | string | 提示信息 |
| data | y | list | data信息 |

## data新加字段说明
| key | Requried | type | description |
|-----|----------|------|-------------| 
| total | y | int | 总条数 |
| address | y | string | 地址 |
| integral | y | int | 积分 |




## sample
```json
{
    
}
```

# GET  /api/wallet/invite/list

## Api description

版本 v1.0

邀请列表接口

## URL Parameter 说明

| key | Requried | type | description |
|-----|:--------:|------|-------------|
| address | y | string | 地址 |
| system | y | string | 设备型号Android or iOS |
| deviceId | y | string | 设备id|
| start | n | int | 查询起始位置 |
| limit | n | int | 查询条数 |

## Response Json说明

| key | Requried | type | description |
|-----|----------|------|-------------| 
| code| y    | number | 返回状态码 为0表示操作成功，其他值参见msg |
| massage | y | string | 提示信息 |
| data | y | list | data信息 |

## data新加字段说明
| key | Requried | type | description |
|-----|----------|------|-------------| 
| total | y | int | 总条数 |
| address | y | string | 被邀请地址 |
| integral | y | int | 积分 |
| create_time | y | int | 邀请时间 |



## sample
```json
{
    
}
```
# POST  /api/wallet/node_info

## Api description

版本 v2.0

获取node节点信息接口，存储钱包内地址数据

## Header Parameter 说明
| key | Requried | type | description |
|-----|:--------:|------|-------------|
| System | y | string | 手机系统：Android/iOS |
| DeviceID | y | string | 设备ID |

## URL Parameter 说明sample
```json
[
	{
		"12sfa4":1
	},
	{
		"12sfa5":1
	}
]
```


## Response Json说明

| key | Requried | type | description |
|-----|----------|------|-------------| 
| code| y    | number | 返回状态码 为0表示操作成功，其他值参见msg |
| massage | y | string | 提示信息 |
| data | y | bool | 返回的数据信息 |

## sample
```json
{
    "code": 0,
    "message": "OK",
    "data": [
        {
            "chainName": "MainChain",
            "chainId": "",
            "sideChainContract": "",
            "mainChainContract": "",
            "fullNode": [
                "47.75.246.83:50051",
                "47.90.243.77:50051",
                "47.90.252.11:50051"
            ],
            "solidityNode": [
                "47.89.187.247:50061",
                "3.225.171.164:50061"
            ]
        },
        {
            "chainName": "DappChain",
            "chainId": "410A6DBD0780EA9B136E3E9F04EBE80C6C288B80EE",
            "sideChainContract": "TJ4apMhB5fhmAwqPcgX9i43SUJZuK6eZj4",
            "mainChainContract": "TAvMDjZpb3MNUJNjXmnYo17MHkLChAu5nT",
            "fullNode": [
                "447.252.85.90:50051",
                "47.252.80.185:50051",
                "47.252.84.141:50051"
            ],
            "solidityNode": [
                "47.252.85.90:50060",
                "47.252.80.185:50060",
                "47.252.84.141:50060"
            ]
        }
    ]
}
```

# POST  /api/wallet/play_screen/deal

## Api description

版本 v2.0

首页弹窗信息点击关闭或查看详情时调用

## Header 说明
| key | Requried | type | description |
|-----|:--------:|------|-------------|
| System | y | string | 类型：Android/iOS |
| DeviceID | y | string | 设备id |
| Version | y | string | 版本号 |

## URL Parameter 说明

| key | Requried | type | description |
|-----|:--------:|------|-------------|
| playId | y | int | 弹窗id|

## Response Json说明

| key | Requried | type | description |
|-----|----------|------|-------------| 
| code| y    | number | 返回状态码 为0表示操作成功，其他值参见msg |
| massage | y | string | 提示信息 |
| data | y | bool | true-验证成功，false-验证失败 |



## sample
```json
{
    "code": 0,
    "message": "OK",
    "data": null
}
```

# GET  /api/wallet/play_screen/info

## Api description

版本 v2.0

首页弹窗信息

## Header 说明
| key | Requried | type | description |
|-----|:--------:|------|-------------|
| System | y | string | 类型：Android/iOS |
| DeviceID | y | string | 设备id |
| Lang | y | int | 语言：1-英语，2-中文 |

## URL Parameter 说明

| key | Requried | type | description |
|-----|:--------:|------|-------------|

## Response Json说明

| key | Requried | type | description |
|-----|----------|------|-------------| 
| code| y    | number | 返回状态码 为0表示操作成功，其他值参见msg |
| massage | y | string | 提示信息 |
| data | y | list | 弹窗信息，空为无弹窗 |


## sample
```json
{
    "code": 0,
    "message": "OK",
    "data": [
        {
            "playId": 1,
            "urlLink": "https://support.tronlink.org/hc/zh-cn/articles/360034519851",
            "imageLink": "https://coin.top/production_test/wallet/home_pop_ch@3x.png"
        }
    ]
}
```

# POST  /api/wallet/sdk_verify

## Api description

版本 v1.0

sdk验证接口

## URL Parameter 说明

| key | Requried | type | description |
|-----|:--------:|------|-------------|
| appID | y | string | AppID |
| secret | y | string | secret |
| sign | y | string | 签名 |

## Response Json说明

| key | Requried | type | description |
|-----|----------|------|-------------| 
| code| y    | number | 返回状态码 为0表示操作成功，其他值参见msg |
| massage | y | string | 提示信息 |
| data | y | bool | true-验证成功，false-验证失败 |



## sample
```json
{
    "code": 0,
    "message": "OK",
    "data": false
}
```

# GET  /api/wallet/lottery/default_data

## Api description

版本 v2.0

默认数据

## URL Parameter 说明

| key | Requried | type | description |
|-----|:--------:|------|-------------|


## Response Json说明

| key | Requried | type | description |
|-----|----------|------|-------------| 
| code| y    | number | 返回状态码 为0表示操作成功，其他值参见msg |
| massage | y | string | 提示信息 |
| data | y | dict | 默认数据 |

## data 说明
| key | Requried | type | description |
|-----|----------|------|-------------|
| startTime | y | int64 | 活动开始时间 |
| endTime | y | int64 | 活动结束时间 |
| activeStatus | y | int | 活动进行状态 0-未开始， 1-进行中， 2-已结束|



## parameter sample
```json
{
    "code": 0,
    "message": "OK",
    "data": {
        "startTime": 1570723200,
        "endTime": 1571673599,
        "activeStatus": 1,
        "winningProbability": "0.06"
    }
}
```

# GET  /api/wallet/lottery/integral

## Api description

版本 v2.0

获取剩余当前积分和可抽奖次数

## URL Parameter 说明

| key | Requried | type | description |
|-----|:--------:|------|-------------|
| address | y | string | 地址 |


## Response Json说明

| key | Requried | type | description |
|-----|----------|------|-------------| 
| code| y    | number | 返回状态码 为0表示操作成功，其他值参见msg |
| massage | y | string | 提示信息 |
| data | y | dict | 默认数据 |

## data 说明
| key | Requried | type | description |
|-----|----------|------|-------------|
| address | y | string | 地址 |
| integral | y | int64 | 剩余积分 |
| drawsNumber | y | int | 剩余抽奖次数|



## parameter sample
```json
{
    "code": 0,
    "message": "OK",
    "data": {
        "address": "TXUtVffQxEuBfGCsTUGSEEhsP6mxshUQo4",
        "integral": 300,
        "drawsNumber": 5
    }
}
```

# GET  /api/wallet/lottery/integral

## Api description

版本 v2.0

获取剩余当前积分和可抽奖次数

## URL Parameter 说明

| key | Requried | type | description |
|-----|:--------:|------|-------------|
| address | y | string | 地址 |


## Response Json说明

| key | Requried | type | description |
|-----|----------|------|-------------| 
| code| y    | number | 返回状态码 为0表示操作成功，其他值参见msg |
| massage | y | string | 提示信息 |
| data | y | dict | 默认数据 |

## data 说明
| key | Requried | type | description |
|-----|----------|------|-------------|
| address | y | string | 地址 |
| integral | y | int64 | 剩余积分 |
| drawsNumber | y | int | 剩余抽奖次数|



## parameter sample
```json
{
    "code": 0,
    "message": "OK",
    "data": {
        "address": "TXUtVffQxEuBfGCsTUGSEEhsP6mxshUQo4",
        "integral": 300,
        "drawsNumber": 5
    }
}
```

# GET  /api/wallet/lottery/record

## Api description

版本 v2.0

获取中奖纪录 参数不传返回最近20条中奖人记录

## URL Parameter 说明

| key | Requried | type | description |
|-----|:--------:|------|-------------|
| address | n | string | 地址 |
| start | n | int | 起始数据 |
| limit | n | int | 查询条数 |


## Response Json说明

| key | Requried | type | description |
|-----|----------|------|-------------| 
| code| y    | number | 返回状态码 为0表示操作成功，其他值参见msg |
| massage | y | string | 提示信息 |
| data | y | dict | 默认数据 |

## data 说明
| key | Requried | type | description |
|-----|----------|------|-------------|
| address | y | string | 地址 |
| integral | y | int64 | 抽奖所消耗积分 |
| resultType | y | int | 得奖代码 1-500trx， 2-200trx， 3-100trx， 4-50trx， 5-1000trx等值能量， 6-1usdt， 7-10000win， 8-5000win|
| result| y | string | 得奖信息|
| createTime | y | int64 | 得奖时间 |



## parameter sample
```json
{
    "code": 0,
    "message": "OK",
    "data": {
        "data": [
            {
                "address": "TKnk1KTjaTeVTUTdoQaZGDwdmg3mmQJrDu",
                "integral": 600,
                "resultType": 6,
                "result": "1usdt",
                "createTime": 1570787057
            },
            {
                "address": "TKnk1KTjaTeVTUTdoQaZGDwdmg3mmQJrDu",
                "integral": 600,
                "resultType": 6,
                "result": "1usdt",
                "createTime": 1570787051
            },
            {
                "address": "TKnk1KTjaTeVTUTdoQaZGDwdmg3mmQJrDu",
                "integral": 600,
                "resultType": 7,
                "result": "10000win",
                "createTime": 1570787046
            },
            {
                "address": "TKnk1KTjaTeVTUTdoQaZGDwdmg3mmQJrDu",
                "integral": 600,
                "resultType": 3,
                "result": "100trx",
                "createTime": 1570787041
            }
        ],
        "total": 7
    }
}
```
# 多签接口变更说明
##1、deal_transaction接口：
##（1）新增参数
| key | Requried | type | description |
|-----|:--------:|------|-------------|
| functionSelector | n | string | trigger contract 方法 demo('transfer(address,uint256)',用于之后解析trigger contract.parameter.value的数据)|

## Response Json说明

| key | Requried | type | description |
|-----|----------|------|-------------| 
| code| y    | number | 返回状态码 为0表示操作成功，其他值参见msg |
| massage | y | string | 提示信息 |

## code状态码说明
| code | msg |
| 0 | success |
| 10001 | Internal server error | 

## 其他code码
```
// 多重签名错误码
	{Code: 20302, Message: "Signature format error"}
	{Code: 20303, Message: "Compute address error"}
	{Code: 20304, Message: "Permission error"}
	{Code: 20305, Message: "Sign old transaction"}
	{Code: 20320, Message: "Other error"}
	// 多重签名广播错误
	{Code: 40001, Message:"SIGERROR"}
	{Code: 40002, Message:"CONTRACT_VALIDATE_ERROR"}
	{Code: 40003, Message:"CONTRACT_EXE_ERROR"}
	{Code: 40004, Message:"BANDWITH_ERROR"}
	{Code: 40005, Message:"DUP_TRANSACTION_ERROR"}
	{Code: 40006, Message:"TAPOS_ERROR"}
	{Code: 40007, Message:"TOO_BIG_TRANSACTION_ERROR"}
	{Code: 40008, Message:"TRANSACTION_EXPIRATION_ERROR"}
	{Code: 40009, Message:"SERVER_BUSY"}
	{Code: 40010, Message:"NO_CONNECTION"}
	Errno{Code: 40011, Message:"NOT_ENOUGH_EFFECTIVE_CONNECTION"}
	{Code: 40012, Message:"OTHER_ERROR"}
```


##2、transaction_record接口参数 state=255返回全部多签交易信息
| key | Requried | type | description |
|-----|:--------:|------|-------------|
| state | y | int | 0-签名中，1-签名交易完成， 2-交易过期/处理失败，255-全部 |

## 返回参数增加三个
| key | Requried | type | description |
|-----|----------|------|-------------|
| state | y | int | 当前交易状态 0-签名中，1-签名交易完成， 2-交易过期， >=3 -处理失败 |
| functionSelector | y | string |trigger contract 方法, demo('transfer(address,uint256)'| 

## signatureProgress 说明 新增签名时间
| key | Requried | type | description |
|-----|----------|------|-------------| 
| address | y | string | 签名地址 |
| weight | y | int | 当前地址权重 |
| isSign | y | int | 当前地址是否已签名：0-未签名， 1-已签名 |
| signTime | y | int | 签名时间 |

## 3、websocket 返回参数新增两个
| key | Requried | type | description |
|-----|----------|------|-------------|
| state | y | int | 当前交易状态 0-签名中，1-签名交易完成， 2-交易过期， 3-处理失败 |
| functionSelector | y | string |trigger contract 方法, demo('transfer(address,uint256)'| 

# POST  /api/wallet/multi/transaction

## Api description

版本 v2.0

提交签名后的交易接口

## URL Parameter 说明

| key | Requried | type | description |
|-----|:--------:|------|-------------|
| address | y | string | 发起者地址 |
| transaction | y | *core.transaction | 签名后的交易 |
| netType | y | string| shasta-测试网， main_net-主网 |

## Response Json说明

| key | Requried | type | description |
|-----|----------|------|-------------| 
| code| y    | number | 返回状态码 为0表示操作成功，其他值参见msg |
| massage | y | string | 提示信息 |


## parameter sample
```json
{
	"address": "TPMQSEwJhkgc2VPdGR3BSn3KV7XBjkqkfu",
	"transaction": {
		"raw_data": {
			"contract": [{
				"Permission_id": 4,
				"parameter": {
					"type_url": "type.googleapis.com/protocol.TriggerSmartContract",
					"value": "0a15418b8eb41da5ad213cde27c5cc44fee834e71c86aa121541ccb7683937b4a201ee7e5b8003e6ce6e226ce4c92244a9059cbb0000000000000000000000416f7c313ee89c7b98aa91e5cbe611c48dc2876d0d0000000000000000000000000000000000000000000000000000000005f5e100"
				},
				"type": "TriggerSmartContract"
			}],
			"expiration": 1561272798826,
			"ref_block_bytes": "ee2b",
			"ref_block_hash": "5e5afea13faba916",
			"timestamp": 1561264639431,
			"feelimit": 20000000
		},
		"signature": ["f178b246031e335ca4d481d22d7cff5d2487192a729e46b309a05ae190c849f92e8028acbdb2b45f2f5b82bb714bc491474abbd86922bb402837dcffffd47d6700", "277390be8efd16b7eced728776c218d5081f2e62bdfd3eb8f5633316663041a8019dbdd2eaced024eb2505bcb396cc9b804081b1e1a57cd346d2d7701c3d03ab01"]
	},
	"netType": "shasta"
}
```
# GET  /api/wallet/multi/trx_record

## Api description

版本 v2.0

签名交易记录接口

## URL Parameter 说明

| key | Requried | type | description |
|-----|:--------:|------|-------------|
| address | y | string | 发起者地址 |
| start | y | int | |
| limit | y | int | |
| state | y | int | 0-签名中，1-签名交易完成， 2-交易过期/处理失败 |
| netType | y | string| shasta-测试网， main_net-主网 |

## Response Json说明

| key | Requried | type | description |
|-----|----------|------|-------------| 
| code| y    | number | 返回状态码 为0表示操作成功，其他值参见msg |
| massage | y | string | 提示信息 |
| data | y | list | 数据信息 |


## data说明
| key | Requried | type | description |
|-----|----------|------|-------------| 
| hash | y | string | 交易hash |
| contractType | y | int | 交易类型 |
| callValue | y | string | transaction中的callvalue|
| originatorAddress | y | string | 发起人 |
| expireTime | y | int | 剩余签名时间 |
| threshold | y | int | 阈值 |
| currentWeight | y | int | 当前权重 |
| signatureProgress | y | list |  签名地址信息 |
| contractData | y | dict | 交易详情 |
| currentTransaction | y | *core.transaction | 当前交易 |
| currentTransaction2 | y | string| 当前交易字符串格式备用 |
|contractData | y | dict | trc20字段信息 |

## signatureProgress 说明
| key | Requried | type | description |
|-----|----------|------|-------------| 
| address | y | string | 签名地址 |
| weight | y | int | 当前地址权重 |
| isSign | y | int | 当前地址是否已签名：0-未签名， 1-已签名 |

## Response sample
```
{
    "code": 0,
    "message": "OK",
    "data": {
        "total": 12,
        "data": [
            {
                "hash": "66799d70be841151f94d3a5cbd440b0a31cd71ba4cf7de8e24650e90c0799c6d",
                "contractType": "TransferContract",
                "callValue": "{\"ref_block_bytes\":\"J6o=\",\"ref_block_hash\":\"8Rnb9bWw4RU=\",\"expiration\":1560239738710,\"contract\":[{\"type\":1,\"parameter\":{\"type_url\":\"type.googleapis.com/protocol.TransferContract\",\"value\":\"ChVBi460HaWtITzeJ8XMRP7oNOcchqoSFUFvfDE+6Jx7mKqR5cvmEcSNwodtDRjAhD0=\"},\"Permission_id\":4}],\"timestamp\":1560233738260}",
                "originatorAddress": "TGjdouJUAuCLbtf8dJtDHuQGfhuPfWcuGo",
                "expireTime": 0,
                "threshold": 2,
                "currentWeight": 2,
                "isSign": 1,
                "signatureProgress": [
                    {
                        "address": "TGjdouJUAuCLbtf8dJtDHuQGfhuPfWcuGo",
                        "weight": 1,
                        "isSign": 1
                    },
                    {
                        "address": "TCsSECZsCeaUiZkeZ1nc1nqLN15H2xrWRe",
                        "weight": 1,
                        "isSign": 1
                    }
                ],
                "contractData": {
                    "amount": 1000000,
                    "owner_address": "TNh7swaW1BnYbJJoe6M36VaRbwh6vbhuoY",
                    "to_address": "TL8gnPX2kVCFZbkHnrKEuUBP2DeG8wRLC1"
                },
                "trc20Info": {
                    "decimals": 6,
                    "name": "TRONAce",
                    "symbol": "ACE"
                },
                "currentTransaction": {
                    "raw_data": {
                        "ref_block_bytes": "27aa",
                        "ref_block_hash": "f119dbf5b5b0e115",
                        "expiration": 1560239738710,
                        "contract": [
                            {
                                "type": "TransferContract",
                                "parameter": {
                                    "type_url": "type.googleapis.com/protocol.TransferContract",
                                    "value": "0a15418b8eb41da5ad213cde27c5cc44fee834e71c86aa1215416f7c313ee89c7b98aa91e5cbe611c48dc2876d0d18c0843d"
                                },
                                "Permission_id": 4
                            }
                        ],
                        "timestamp": 1560233738260
                    },
                    "signature": [
                        "008a7f63a41d8236a72b6b332f6a8a93d2f798fdeb9c981000b6df2152f659750f26b0134040af0480bb5048556fa89415c11f3f8fd72af8939b162b6b95de7f01",
                        "60b5dc67e6e8b9a4fd159befb0ade870031edc35c109b0b119f5671b3e68939c2e5e20c692746b55b1a3350b56eb27b7ebdfd89cb92e6238f8752d5cbefe30bf00"
                    ]
                },
                "currentTransaction2": "{\"raw_data\":{\"ref_block_bytes\":\"27aa\",\"ref_block_hash\":\"f119dbf5b5b0e115\",\"expiration\":1560239738710,\"contract\":[{\"type\":\"TransferContract\",\"parameter\":{\"type_url\":\"type.googleapis.com/protocol.TransferContract\",\"value\":\"0a15418b8eb41da5ad213cde27c5cc44fee834e71c86aa1215416f7c313ee89c7b98aa91e5cbe611c48dc2876d0d18c0843d\"},\"Permission_id\":4}],\"timestamp\":1560233738260},\"signature\":[\"008a7f63a41d8236a72b6b332f6a8a93d2f798fdeb9c981000b6df2152f659750f26b0134040af0480bb5048556fa89415c11f3f8fd72af8939b162b6b95de7f01\",\"60b5dc67e6e8b9a4fd159befb0ade870031edc35c109b0b119f5671b3e68939c2e5e20c692746b55b1a3350b56eb27b7ebdfd89cb92e6238f8752d5cbefe30bf00\"]}"
            }
        ]
    }
}

```

# GET  /api/wallet/multi/socket

## Api description

版本 v2.0

待签名的交易接口

## URL Parameter 说明

| key | Requried | type | description |
|-----|:--------:|------|-------------|
| address | y | string | 发起者地址 |
| netType | y | string| shasta-测试网， main_net-主网 |


## Response Json说明

| key | Requried | type | description |
|-----|----------|------|-------------| 
| code| y    | number | 返回状态码 为0表示操作成功，其他值参见msg |
| massage | y | string | 提示信息 |
| data | y | list | 数据信息 |


## data说明
| key | Requried | type | description |
|-----|----------|------|-------------| 
| hash | y | string | 交易hash |
| contractType | y | int | 交易类型 |
| callValue | y | string | transaction中的callvalue|
| originatorAddress | y | string | 发起人 |
| expireTime | y | int | 剩余签名时间 |
| threshold | y | int | 阈值 |
| currentWeight | y | int | 当前权重 |
| signatureProgress | y | list |  签名地址信息 |
| contractData | y | dict | 交易详情 |
| currentTransaction | y | *core.transaction | 当前交易 |
| currentTransaction2 | y | string| 当前交易字符串格式备用 |
| contractData | y | dict | trc20字段信息 |

## signatureProgress 说明
| key | Requried | type | description |
|-----|----------|------|-------------| 
| address | y | string | 签名地址 |
| weight | y | int | 当前地址权重 |
| isSign | y | int | 当前地址是否已签名：0-未签名， 1-已签名 |

## sample
```
{
    "code": 0,
    "message": "OK",
    "data": {
        "total": 12,
        "data": [
            {
                "hash": "66799d70be841151f94d3a5cbd440b0a31cd71ba4cf7de8e24650e90c0799c6d",
                "contractType": "TransferContract",
                "callValue": "{\"ref_block_bytes\":\"J6o=\",\"ref_block_hash\":\"8Rnb9bWw4RU=\",\"expiration\":1560239738710,\"contract\":[{\"type\":1,\"parameter\":{\"type_url\":\"type.googleapis.com/protocol.TransferContract\",\"value\":\"ChVBi460HaWtITzeJ8XMRP7oNOcchqoSFUFvfDE+6Jx7mKqR5cvmEcSNwodtDRjAhD0=\"},\"Permission_id\":4}],\"timestamp\":1560233738260}",
                "originatorAddress": "TGjdouJUAuCLbtf8dJtDHuQGfhuPfWcuGo",
                "expireTime": 0,
                "threshold": 2,
                "currentWeight": 2,
                "isSign": 1,
                "signatureProgress": [
                    {
                        "address": "TGjdouJUAuCLbtf8dJtDHuQGfhuPfWcuGo",
                        "weight": 1,
                        "isSign": 1
                    },
                    {
                        "address": "TCsSECZsCeaUiZkeZ1nc1nqLN15H2xrWRe",
                        "weight": 1,
                        "isSign": 1
                    }
                ],
                "contractData": {
                    "amount": 1000000,
                    "owner_address": "TNh7swaW1BnYbJJoe6M36VaRbwh6vbhuoY",
                    "to_address": "TL8gnPX2kVCFZbkHnrKEuUBP2DeG8wRLC1"
                },
                "trc20Info": {
                	"decimals": 6,
                	"name": "TRONAce",
                	"symbol": "ACE"
                },
                "currentTransaction": {
                    "raw_data": {
                        "ref_block_bytes": "27aa",
                        "ref_block_hash": "f119dbf5b5b0e115",
                        "expiration": 1560239738710,
                        "contract": [
                            {
                                "type": "TransferContract",
                                "parameter": {
                                    "type_url": "type.googleapis.com/protocol.TransferContract",
                                    "value": "0a15418b8eb41da5ad213cde27c5cc44fee834e71c86aa1215416f7c313ee89c7b98aa91e5cbe611c48dc2876d0d18c0843d"
                                },
                                "Permission_id": 4
                            }
                        ],
                        "timestamp": 1560233738260
                    },
                    "signature": [
                        "008a7f63a41d8236a72b6b332f6a8a93d2f798fdeb9c981000b6df2152f659750f26b0134040af0480bb5048556fa89415c11f3f8fd72af8939b162b6b95de7f01",
                        "60b5dc67e6e8b9a4fd159befb0ade870031edc35c109b0b119f5671b3e68939c2e5e20c692746b55b1a3350b56eb27b7ebdfd89cb92e6238f8752d5cbefe30bf00"
                    ]
                },
                "currentTransaction2": "{\"raw_data\":{\"ref_block_bytes\":\"27aa\",\"ref_block_hash\":\"f119dbf5b5b0e115\",\"expiration\":1560239738710,\"contract\":[{\"type\":\"TransferContract\",\"parameter\":{\"type_url\":\"type.googleapis.com/protocol.TransferContract\",\"value\":\"0a15418b8eb41da5ad213cde27c5cc44fee834e71c86aa1215416f7c313ee89c7b98aa91e5cbe611c48dc2876d0d18c0843d\"},\"Permission_id\":4}],\"timestamp\":1560233738260},\"signature\":[\"008a7f63a41d8236a72b6b332f6a8a93d2f798fdeb9c981000b6df2152f659750f26b0134040af0480bb5048556fa89415c11f3f8fd72af8939b162b6b95de7f01\",\"60b5dc67e6e8b9a4fd159befb0ade870031edc35c109b0b119f5671b3e68939c2e5e20c692746b55b1a3350b56eb27b7ebdfd89cb92e6238f8752d5cbefe30bf00\"]}"
            }
        ]
    }
}

```





