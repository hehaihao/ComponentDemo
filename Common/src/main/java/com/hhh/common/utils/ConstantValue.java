package com.hhh.common.utils;

/**
 * 文件描述：常量d
 */
public class ConstantValue {

    // 主网钱包私钥上传前进行AES加密的key
    public static final String PRIVATE_KEY_AES_PWD = "xm6leefun@mding_888";

    public static final String QR_CODE_HEAD = "chatfeng=";
    public static final String PAY_QR_CODE_KEYWORD = "chatfeng_mding_wallet";
    public static final String QR_CODE_WALLET_OC_FLAG = "qrCodeWalletOC//";  // 含有该字段表明是主网钱包的二维码
    public static final String QR_CODE_ADDRESS_FLAG = "address=";  // 二维码地址连接字段

    public static final String RECEIVE_QR_CODE_ETH_HEAD= "ethereum:";  // 以太坊收款码头部

    public static final String CURRENT_NODE = "current_node";  //当前选中的节点key
    public static final String OLD_NODE = "old_node";  //上次选中的节点key
    public static final String AD_TIME = "ad_time";  // 公告发布的时间
}
