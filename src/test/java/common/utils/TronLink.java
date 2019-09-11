package common.utils;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.OutputType;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.imageio.ImageIO;

import io.appium.java_client.touch.WaitOptions;

public class TronLink {

  public static String tronLinkUrl = "http://localhost:4723/wd/hub";
  //public static String tronLinkUrl = "http://192.168.56.101:5555";

  public static String tronLinkApk = "/Users/tron/Documents/testnet-tronlink.apk";
  //public static String tronLinkApk = "/Users/wangzihe/Desktop/tronlink_baidu_v3.1.0.apk";
//public static String tronLinkApk = "/Users/wangzihe/Documents/Android-iTRON-clone/app/baidu/release/app-baidu-release.apk";
  public static String platformVersion = "9";
  public static String deviceName = "Android Device";
  //public static String deviceName = "192.168.56.101:5555";
  public static String platformName = "Android";
  private static final int QRCOLOR = 0xFF000000;
  private static final int BGWHITE = 0xFFFFFFFF;
  private static final int WIDTH = 400;
  private static final int HEIGHT = 400;
  public static String mnemonicText = "";
  public static String walletAddress = "";
  public static String walletPrivateKey = "";
  public static String importAccountId = "com.tronlink.wallet:id/tv_import";
  public static String createAccountId = "com.tronlink.wallet:id/tv_create";
  public static String addWallet = "com.tronlink.wallet:id/tv_create";
  public static String sendCoinId = "com.tronlink.wallet:id/rl_send";
  public static String receiveCoinId = "com.tronlink.wallet:id/rl_receive";
  public static String receiveScreenSameQRCodeId = "com.tronlink.wallet:id/tv_common_right2";
  public static String receiveScreenAddressTextId = "com.tronlink.wallet:id/address";
  public static String copyAddressIconId = "com.tronlink.wallet:id/copy";
  public static String tronLendingId = "com.tronlink.wallet:id/rl_energy_lease";
  public static String voteId = "com.tronlink.wallet:id/rl_vote";
  public static String voteResetId = "com.tronlink.wallet:id/reset";
  public static String voteSlectionInputId = "com.tronlink.wallet:id/et_input";
  public static String voteInputQuantityXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[3]/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.EditText";
  public static Long voteQuantity = 2L;
  public static String voteNowId = "com.tronlink.wallet:id/bt_go";
  public static String addAssetId = "com.tronlink.wallet:id/rl_add_assets";
  public static String enter_NameIdContractAddress_InputBox_id = "com.tronlink.wallet:id/et_search";
  public static String energyOptionIconId = "com.tronlink.wallet:id/tv_energy";
  public static String bandwidthOptionIconId = "com.tronlink.wallet:id/tv_bandwidth";
  public static String bandwidthQuestionId = "com.tronlink.wallet:id/bandwidth_question";
  public static String energyQuestionId = "com.tronlink.wallet:id/bandwidth_question";
  public static String bandwidthQuestionContentId = "com.tronlink.wallet:id/content";
  public static String energyQuestionContentId = "com.tronlink.wallet:id/content";
  public static String balanceInFrozenScreenId = "com.tronlink.wallet:id/current_use";
  public static String trxValueInAssetScreenId = "com.tronlink.wallet:id/tv_trx_value";
  public static String frozenQuantityInputId = "com.tronlink.wallet:id/et_freeze_count";
  public static String freeezeUnfreezeId = "com.tronlink.wallet:id/rl_freeze_unfreeze";
  public static String sendCoinAmountId = "com.tronlink.wallet:id/et_count";
  public static Long sendCoinAmount = 1L;
  public static Long frozenQuantityForBandwidth = 2L;
  public static Long frozenQuantityForEnergy = 3L;
  public static Long assetIdOfQuery = 1000001L;
  public static String freezeIconId = "com.tronlink.wallet:id/freeze";
  public static String freezeNowIconId = "com.tronlink.wallet:id/bt_go";
  public static String freezeRuleId = "com.tronlink.wallet:id/tv_common_right2";
  public static String freezeDoc1Id = "com.tronlink.wallet:id/doc0_spe";
  public static String freezeEnergyDetailId = "com.tronlink.wallet:id/ll_energy_arrow";
  public static String freezeBandwidthDetailId = "com.tronlink.wallet:id/ll_bandwidth_arrow";
  public static String myFreezeEnergyAmountId = "com.tronlink.wallet:id/tv_myfreeze";
  public static String myFreezeBandwidthAmountid = "com.tronlink.wallet:id/tv_myfreeze_bandwidth";
  public static String otherFreezeBandwidthAmountId = "com.tronlink.wallet:id/tv_otherfreeze_bandwidth";
  public static String otherFreezeEnergyAmountId = "com.tronlink.wallet:id/tv_otherfreeze";
  public static String totalFreezeEnergyAmountId = "com.tronlink.wallet:id/tv_totalfreeze";
  public static String totalFreezeBandwidthAmountId = "com.tronlink.wallet:id/tv_totalfreeze_bandwidth";
  public static String votingPowerInFreezeId = "com.tronlink.wallet:id/tv_voting_power";
  public static String sendCoinButtonId = "com.tronlink.wallet:id/send";
  public static String transferNowId = "com.tronlink.wallet:id/bt_go";
  public static String transactionConfirmButtonId = "com.tronlink.wallet:id/bt_send";
  public static String transactionConfirmInputPasswordId = "com.tronlink.wallet:id/et_new_password";
  public static String receiveAddressId = "com.tronlink.wallet:id/et_address";
  public static String acceptImportAccount = "com.tronlink.wallet:id/bt_accept";
  public static String assetsCount = "com.tronlink.wallet:id/assets_count";
  public static String assetIconId = "com.tronlink.wallet:id/assets";
  public static String assetDisplayAreaId = "com.tronlink.wallet:id/rl_inner";
  public static String assetSwitchId = "com.tronlink.wallet:id/iv_switch";
  public static String assetsDisplayedXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout[2]";
  public static String assetsDisplayedFirstElementXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.support.v4.view.ViewPager/android.widget.RelativeLayout/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.RelativeLayout";
  public static String marketsIconId = "com.tronlink.wallet:id/appmarket";
  public static String priceChangeId = "com.tronlink.wallet:id/tv_change";
  public static String lastestPriceXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.TextView";
  public static String firstPriceOfPriceChangeXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.TextView";
  public static String firstPriceOfLastestPriceXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.TextView[1]";
  public static String marketsExchangeListId = "com.tronlink.wallet:id/rl_list";
  public static String marketsSearchId = "com.tronlink.wallet:id/iv_search";
  public static String marketsSearchInputId = "com.tronlink.wallet:id/et_search";
  public static String firstExchangeInMarketsSearchScreenXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.TextView[1]";
  public static String discoverIconId = "com.tronlink.wallet:id/app1";
  public static String discoverSearchEnterId = "com.tronlink.wallet:id/iv_one";
  public static String discoverSearchInputId = "com.tronlink.wallet:id/et_search";
  public static String discoverSearchConfirmId = "com.tronlink.wallet:id/tv_search";
  public static String discoverSearchResultNameId = "com.tronlink.wallet:id/tv_name";
  public static String discoverSearchresultDescriptionId = "com.tronlink.wallet:id/tv_description";
  public static String discoverSearchFirstResultXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]";
  public static String enterConfirmIconOfFirstDiscoverSearchId = "com.tronlink.wallet:id/tv_ok";
  public static String enterCancelIconOfFirstDiscoverSearchId = "com.tronlink.wallet:id/tv_cancle";
  public static String discoverSearchHistoryId = "com.tronlink.wallet:id/imageview";
  public static String discoverSearchScanId = "com.tronlink.wallet:id/iv_scan";
  public static String meIconId = "com.tronlink.wallet:id/my";
  public static String meFriendInvitationId = "com.tronlink.wallet:id/tv_friend_invitation";
  public static String meAnnouncementId = "com.tronlink.wallet:id/tv_an";
  public static String meJoinOurCommunitiesId = "com.tronlink.wallet:id/join_community";
  public static String meJoinOurCommunitiesEnglishTelegraphGroupId = "com.tronlink.wallet:id/rl_en_arrow";
  public static String meJoinOurCommunitiesChineseTelegraphGroupId = "com.tronlink.wallet:id/rl_zh_arrow";
  public static String meJoinOurCommunitiesTwitterId = "com.tronlink.wallet:id/rl_twitter_arrow";
  public static String meJoinOurCommunitiesWechatId = "com.tronlink.wallet:id/rl_twitter_arrow";
  public static String meHelpCenterId = "com.tronlink.wallet:id/help";
  public static String meAboutUsId = "com.tronlink.wallet:id/about";
  public static String meAboutUsVersionLogsId = "com.tronlink.wallet:id/log";
  public static String meAbountUsVersionVersionUpdateId = "com.tronlink.wallet:id/update";
  public static String mutliSignatureManagementId = "com.tronlink.wallet:id/rl_sign_manager";
  public static String mutliSignQuestionId = "com.tronlink.wallet:id/iv_qr";
  public static String mutliSignQuestionContentXPath = "/hierarchy/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.TextView";
  public static String mutliSignAddPermissionId = "com.tronlink.wallet:id/bt_go";
  public static String activeScreenMoreId = "com.tronlink.wallet:id/ll_more";
  public static String modifyPermissionsId = "com.tronlink.wallet:id/ll_edit";
  public static String deletePermissionId = "com.tronlink.wallet:id/ll_delete";
  public static String deletePermissionConfirmId = "com.tronlink.wallet:id/tv_ok";
  public static String deletePermissionCancelId = "com.tronlink.wallet:id/tv_cancle";
  public static String permissionOperationId = "com.tronlink.wallet:id/fl_operations";
  public static String addPermissionId = "com.tronlink.wallet:id/bt_go";
  public static String permissionNameInputId = "com.tronlink.wallet:id/et_permission_name";
  public static String trxTransferOperationInAddPermissionXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.TextView";
  public static String trc10TransferOperationInAddPermissionXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.TextView";
  public static String trc20TransferOperationInAddPermissionXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.TextView";
  public static String thresholdInAddPermissionInputId = "com.tronlink.wallet:id/et_threshold";
  public static String permissionAddressInputId = "com.tronlink.wallet:id/et_key_address";
  public static String permissionWeightInputId = "com.tronlink.wallet:id/et_weight";
  public static String addKeyInAddPermissionId = "com.tronlink.wallet:id/rl_add_key_button";
  public static String secondPermissionAddressInputXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout[5]/android.widget.LinearLayout[2]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.EditText[1]";
  public static String secondPermissionWeightInputXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout[5]/android.widget.LinearLayout[2]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.EditText[2]";
  public static String addPermissionConfirmId = "com.tronlink.wallet:id/tv_confirm";
  public static String updateAccountPermissionServiceChargeId = "com.tronlink.wallet:id/tv_trx_amount";
  public static String updateAccountPermissionInputPasswordId = "com.tronlink.wallet:id/et_new_password";
  public static String updateAccountPermissionPayIconId = "com.tronlink.wallet:id/bt_send";

  public static String transactionHistoryId = "com.tronlink.wallet:id/transfer_history";
  public static String transactionHistoryQueryWalletId = "com.tronlink.wallet:id/iv_qr";
  public static String transactionHistoryWalletResultNameId = "com.tronlink.wallet:id/tv_name";
  public static String transactionHistoryReceiveXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab[2]/android.widget.TextView";
  public static String transactionHistorySentXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab[3]/android.widget.TextView";
  public static String transactionHistoryAllXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab[1]/android.widget.TextView";
  public static String transactionHistorySentFirstResultConfirmedXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab[1]/android.widget.TextView";
  public static String transactionHistorySentFirstResultAddressXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.support.v4.view.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.TextView[1]";
  public static String transactionHistorySentFirstResultContractTypeXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.support.v4.view.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.TextView[2]";
  public static String transactionHistorySentFirstResultAmountXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.support.v4.view.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView[1]";
  public static String transactionHistorySentFirstResultDateXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.support.v4.view.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.RelativeLayout/android.widget.TextView";
  public static String transactionHistoryReceivedFirstResultAddressXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.support.v4.view.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView[2]";
  public static String privateKey = "com.tronlink.wallet:id/cd_pk";
  public static String enterContent = "com.tronlink.wallet:id/et_content";
  public static String nextStep = "com.tronlink.wallet:id/bt_next";
  public static String qrButton = "com.tronlink.wallet:id/iv_qr";
  public static String systemAllow = "com.android.packageinstaller:id/permission_allow_button";
  public static String mnemonic = "com.tronlink.wallet:id/rl_mm";
  public static String hdWallet = "com.tronlink.wallet:id/ll_hd_wallet";
  public static String keystore = "com.tronlink.wallet:id/cd_kt";
  public static String watchWallet = "com.tronlink.wallet:id/cd_ow";
  public static String createWallet = "com.tronlink.wallet:id/cd_cw";
  public static String setUpName = "com.tronlink.wallet:id/et_name";
  public static String creatNextStep = "com.tronlink.wallet:id/creat";
  public static String passWord = "com.tronlink.wallet:id/et_password";
  public static String creatNextStep2 = "com.tronlink.wallet:id/creat";
  public static String creatNextStep3 = "com.tronlink.wallet:id/creat";
  public static String riskIgnore = "com.tronlink.wallet:id/tv_cancle";
  public static String riskBackup = "com.tronlink.wallet:id/tv_ok";
  public static String addressText = "com.tronlink.wallet:id/tv_address";
  public static String walletPassword = "com.tronlink.wallet:id/et_password";
  public static String confirm = "com.tronlink.wallet:id/tv_ok";
  public static String privateKeyText = "com.tronlink.wallet:id/tv_privatekey";
  public static String keystoreText = "com.tronlink.wallet:id/tv_keystore";
  public static String done = "com.tronlink.wallet:id/backup";
  public static String backUpNow = "com.tronlink.wallet:id/backup";
  public static String gotItButton = "com.tronlink.wallet:id/bt_know";
  public static String saveKey = "com.tronlink.wallet:id/save";
  public static String keyIndexText = "com.tronlink.wallet:id/text";
  public static String itemText = "com.tronlink.wallet:id/tv_item";
  public static String numberIndex = "com.tronlink.wallet:id/tv_number";
  public static String nextStepButton = "com.tronlink.wallet:id/tv_next";
  public static String totalAssets = "com.tronlink.wallet:id/tv_total_assets";
  public static String dappUrl = "com.tronlink.wallet:id/et_url";
  public static String dappButton = "com.tronlink.wallet:id/bt";

  public static String tabAssets = "com.tronlink.wallet:id/assets";
  public static String tabAppmarket = "com.tronlink.wallet:id/appmarket";
  public static String tabApp1 = "com.tronlink.wallet:id/app1";
  public static String tabMy = "com.tronlink.wallet:id/my";
  public static String settings = "com.tronlink.wallet:id/setting";
  public static String setting_languane = "com.tronlink.wallet:id/languane";
  public static String setting_currency = "com.tronlink.wallet:id/money";
  public static String setting_node = "com.tronlink.wallet:id/node";
  public static String setting_developer = "com.tronlink.wallet:id/testnode";
  public static String setting_conversion = "com.tronlink.wallet:id/convert";
  public static String setting_dapp = "com.tronlink.wallet:id/dapp";

  public static String language_title = "com.tronlink.wallet:id/title";
  public static String moneyValue = "com.tronlink.wallet:id/tv_money_value";
  public static String mnemonicTool = "com.tronlink.wallet:id/et_innertitle";
  public static String oneClickConvert = "com.tronlink.wallet:id/bt_convert";
  public static String nodeShastText = "com.tronlink.wallet:id/tv_node_shast";
  public static String balance = "com.tronlink.wallet:id/tv_balance";
  public static String walletBalance = "com.tronlink.wallet:id/tv_blance";
  public static String manageropen = "com.tronlink.wallet:id/manageropen";
  public static String selectWallet = "com.tronlink.wallet:id/rl_selected";
  public static String pictureName = "android:id/title";
  public static String imageView = "android.widget.ImageView";

  public static String my_walletManager = "com.tronlink.wallet:id/wallet_manager";
  public static String deleteWallet = "com.tronlink.wallet:id/delete";
  public static String deleteWallet2 = "com.tronlink.wallet:id/delete2";
  public static String deleteWalletTop = "com.tronlink.wallet:id/delete_top";
  public static String etPassword = "com.tronlink.wallet:id/et_password";
  public static String backupMnemonic = "com.tronlink.wallet:id/rl_mnemonic";
  public static String backupPrivateKey = "com.tronlink.wallet:id/rl_privatekey";
  public static String backupKeystore = "com.tronlink.wallet:id/rl_keystore";
  public static String testPrivateKey = "ecd4bbba178b1b0d2a0c1e6e9108e0cab805a2c1365c42c9eafaff104dbf1e72";
  public static String receiverAddress = "TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp";
  public static String ownerAddress = "TMNQnpTsNHuK1NwqMf6WTBydXvNsv9p6of";
  public static String activePermissionManager1Address = "THph9K2M2nLvkianrMGswRhz5hjSA9fuH7";
  public static String activePermissionManager2Address = "TV75jZpdmP2juMe1dRwGrwpV6AMU6mr1EU";
  public static String testPassword = "Test0001";


  private AndroidDriver driver;
  public static AndroidTouchAction action;


  public static DesiredCapabilities getTronLinkDesiredCapabilities(
          DesiredCapabilities desiredCapabilities) {
    desiredCapabilities.setCapability("deviceName", TronLink.deviceName);
    desiredCapabilities.setCapability("platformName", TronLink.platformName);
    desiredCapabilities.setCapability("platformVersion", TronLink.platformVersion);
    desiredCapabilities.setCapability("app", TronLink.tronLinkApk);
    return desiredCapabilities;
  }

  public static void waitTargetElementAppear(AndroidDriver driver) {
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    return;
  }




  public static void loadingPageWaitingAction(AndroidDriver driver, String resId) {
    int repeatTimes = 1;
    if (resId.equals(voteId) || resId.equals(marketsIconId) || resId.equals(meIconId) || resId.equals(assetIconId)){
      repeatTimes = 300;
    }
    System.out.println("repeat times:" + repeatTimes);
    while (repeatTimes-- > 0) {
      TronLink.waitTargetElementAppear(driver);
    }
    return;
  }

  //click action
  public static void testOperation(AndroidDriver driver, String resId, String step ,String description) {
    testOperation(driver, resId, step, "",description);
  }
  //swipe action
  public static void testOperation(AndroidDriver driver, String step, String description) {
    testOperation(driver, "", step, "",description);
  }

  public static void testOperation(AndroidDriver driver, String resId, String action, String input, String description) {
//    getScreenshot(driver,description);
    waitTargetElementAppear(driver);
    MobileElement element = null;
    if (!resId.isEmpty()) {
      if (resId.indexOf("com.tronlink.wallet:id") != -1){
        element = (MobileElement) driver.findElementById(resId);
      }else {
        element = (MobileElement) driver.findElement(MobileBy.xpath(resId));

      }
    }
    switch (action) {
      case "click":
        element.click();
        break;
      case "input":
        element.sendKeys(input);
        if (input.equals(testPassword)) {
          driver.navigate().back();
        } else {
          driver.hideKeyboard();
        }

        break;
      case "swipeUp":
        swipeUp(driver);
        break;
      case "swipeDown":
        swipeDown(driver);
        break;
      case "swipeRight":
        swipeRight(driver);
        break;
      case "swipeLeft":
        swipeLeft(driver);
        break;
    }
    activeLoadingPage(driver,resId);
  }

  public static void swipeUp(AndroidDriver driver){
    AndroidTouchAction action = new AndroidTouchAction(driver);
    int width = driver.manage().window().getSize().width;
    int height = driver.manage().window().getSize().height;
    System.out.print("   " + width + "   " + height);
    Duration duration = Duration.ofMillis(200);
    action.press(
            PointOption.point(width/2, height*4/5))
            .waitAction(WaitOptions.waitOptions(duration))
            .moveTo(PointOption.point(width/2, height/5))
            .release().perform();
  }

  public static void swipeDown(AndroidDriver driver){
    AndroidTouchAction action = new AndroidTouchAction(driver);
    int width = driver.manage().window().getSize().width;
    int height = driver.manage().window().getSize().height;
    //System.out.print("   " + width + "   " + height);
    Duration duration = Duration.ofMillis(150);
    action.press(
            PointOption.point(width/2, height/5))
            .waitAction(WaitOptions.waitOptions(duration))
            .moveTo(PointOption.point(width/2, height*4/5))
            .release().perform();
  }

  public static void activeLoadingPage(AndroidDriver driver,String resId) {
    if (!resId.equals(voteId)) {
      return;
    }
    AndroidTouchAction action = new AndroidTouchAction(driver);
    int width = driver.manage().window().getSize().width;
    int height = driver.manage().window().getSize().height;
    Duration duration = Duration.ofMillis(1500);
    int times = 0;
    while (times++ < 500) {
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    action.press(
            PointOption.point(width/2, height*750/2094))
            .waitAction(WaitOptions.waitOptions(duration))
            .moveTo(PointOption.point(width/2, height*750/2094))
            .release().perform();

    while (times++ < 1500) {
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
  }

  public static void swipeRight(AndroidDriver driver){
    AndroidTouchAction action = new AndroidTouchAction(driver);
    int width = driver.manage().window().getSize().width;
    int height = driver.manage().window().getSize().height;
    System.out.print("   " + width + "   " + height);
    Duration duration = Duration.ofMillis(150);
    action.press(
            PointOption.point(width*3/4, height/2))
            .waitAction(WaitOptions.waitOptions(duration))
            .moveTo(PointOption.point(width/4, height/2))
            .release().perform();
  }

  public static void swipeLeft(AndroidDriver driver){
    AndroidTouchAction action = new AndroidTouchAction(driver);
    int width = driver.manage().window().getSize().width;
    int height = driver.manage().window().getSize().height;
    System.out.print("   " + width + "   " + height);
    Duration duration = Duration.ofMillis(150);
    action.press(
            PointOption.point(width/4, height/2))
            .waitAction(WaitOptions.waitOptions(duration))
            .moveTo(PointOption.point(width*3/4, height/2))
            .release().perform();
  }

  //get screenshot
  public static void getScreenshot(AndroidDriver driver,String description){
    waitTargetElementAppear(driver);
    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
    String date = df.format(new Date());
    if(description.equals("got it") || description.equals("back up now")) {
      return;
    }
    File screen = ((RemoteWebDriver) driver).getScreenshotAs(OutputType.FILE);
    File screenFile = new File("build/reports/tests/tronlink/screenShot/"+date+description+".png");
    try {
      org.apache.commons.io.FileUtils.copyFile(screen,screenFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static boolean isElement(AndroidDriver driver,String element){
    String pageSource = driver.getPageSource();
    System.out.println(pageSource);
    if (pageSource.indexOf(element) == -1){
      return false;
    }else{
      return true;
    }
  }

  public static boolean isEnabled(AndroidDriver driver,String element){
    return driver.findElementById(element).isEnabled();
  }

  public static boolean isDisplayed(AndroidDriver driver,String element){
    return driver.findElementById(element).isDisplayed();
  }

  public static ArrayList<String> getTextList(AndroidDriver driver,String element){
    List<MobileElement> text = driver.findElementsById(element);
    ArrayList<String> TextList = new ArrayList<>();
    for (MobileElement data : text){
      TextList.add(data.getText());
    }
    return TextList;
  }


  public static String getText(AndroidDriver driver,String elementIdOrXPath){
    waitTargetElementAppear(driver);
    MobileElement element = null;
    if (!elementIdOrXPath.isEmpty()) {
      if (elementIdOrXPath.indexOf("com.tronlink.wallet:id") != -1){
        element = (MobileElement) driver.findElementById(elementIdOrXPath);
      }else {
        element = (MobileElement) driver.findElement(MobileBy.xpath(elementIdOrXPath));

      }
    }
    String text = element.getText();
    return text;
  }

  public static int getSameMnemonicIdex(AndroidDriver driver,ArrayList<String> allTextList,String confirmItem,String numberIndex){
    List<String> confirmList = TronLink.getTextList(driver,confirmItem);
    int number = 0;
    if (confirmList.size() > 6){
      List<MobileElement> numberList = driver.findElementsById(numberIndex);
      number = Integer.parseInt(numberList.get(1).getText().substring(1)) - 1;
      confirmList = TronLink.getTextList(driver,confirmItem).subList(6,12);
    }else {
      number = Integer.parseInt(driver.findElementById(numberIndex).getText().substring(1)) - 1;
    }
    int flag = confirmList.indexOf(allTextList.get(number));
    return flag;
  }

  public static void screenOn() {
    try {
      Runtime rt = Runtime.getRuntime();
      Process p = rt.exec("adb shell dumpsys power | findstr \"Display Power:state=\"");
      BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
      String line;
      String content = "";
      while ((line = in.readLine()) != null) {
        content = content + line;
      }
      if (content.contains("Display Power: state=OFF")) {
        Runtime.getRuntime().exec("adb shell input keyevent 26");
        Runtime.getRuntime().exec("adb shell input keyevent 3");
      }
      p.destroy();
    } catch (IOException ex) {
      return;
    }
  }

  public static AndroidDriver importWallet(AndroidDriver driver,String privateKey) {
    try {
      TronLink.testOperation(driver, TronLink.importAccountId, "click", "click import Account");
      while (!TronLink.isEnabled(driver,TronLink.acceptImportAccount)){
        TronLink.testOperation(driver,"swipeUp","");
      }
      TronLink.testOperation(driver, TronLink.acceptImportAccount, "click", "click Accept");
      //use Private Key import account
      TronLink.testOperation(driver, TronLink.privateKey, "click", "click Private key");
      TronLink.testOperation(driver, TronLink.enterContent, "input", privateKey, "enter private key");
      TronLink.testOperation(driver, TronLink.nextStep, "click", "click Next step");
      Date date = new Date();
      String timestamp = String.valueOf(date.getTime());
      TronLink.testOperation(driver, TronLink.setUpName, "input", "Test_" + timestamp, "input name");
      TronLink.testOperation(driver, TronLink.creatNextStep, "click", "1:input name");
      TronLink.testOperation(driver, TronLink.passWord, "input", testPassword, "input password");
      TronLink.testOperation(driver, TronLink.creatNextStep2, "click", "2:click next step");
      TronLink.testOperation(driver, TronLink.passWord, "input", testPassword, "input password again");
      TronLink.testOperation(driver, TronLink.creatNextStep3, "click", "3:click carry out");

      int times = 0;
      while (times++ < 500) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      }
      AndroidTouchAction action = new AndroidTouchAction(driver);
      int width = driver.manage().window().getSize().width;
      int height = driver.manage().window().getSize().height;
      Duration duration = Duration.ofMillis(1500);
      action.press(
              PointOption.point(width/2, height*1/2))
              .waitAction(WaitOptions.waitOptions(duration))
              .moveTo(PointOption.point(width/2, height*1/2))
              .release().perform();
      while (times++ < 1000) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      }
      driver.pressKey(new KeyEvent(AndroidKey.BACK));
      waitTargetElementAppear(driver);

    }
    catch (Exception ex) {
      System.out.print(ex);
      return null;
    }
    return driver;
  }

  public static AndroidDriver createWallet(AndroidDriver driver) {
    try {
      //startup page
      TronLink.getScreenshot(driver,"Startup page");
      TronLink.testOperation(driver, TronLink.importAccountId,"click","click import Account");
      while (!TronLink.isEnabled(driver,TronLink.acceptImportAccount)){
        TronLink.testOperation(driver,"swipeUp","");
      }
      TronLink.testOperation(driver,TronLink.acceptImportAccount,"click","click Accept");

      //create account
      TronLink.testOperation(driver,TronLink.createWallet,"click","click create wallet");
      Date date = new Date();
      String timestamp = String.valueOf(date.getTime());
      TronLink.testOperation(driver,TronLink.setUpName,"input","Test_"+timestamp,"input name");
      TronLink.testOperation(driver,TronLink.creatNextStep,"click","1:input name");
      TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
      TronLink.testOperation(driver,TronLink.creatNextStep2,"click","2:click next step");
      TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password again");
      TronLink.testOperation(driver,TronLink.creatNextStep3,"click","3:click carry out");

      //backup mnemonic
      TronLink.testOperation(driver,TronLink.backUpNow,"click","back up now");
      TronLink.testOperation(driver,TronLink.gotItButton,"click","got it");

      //backup mnemonic
      TronLink.testOperation(driver,"swipeUp","back up now");
      TronLink.testOperation(driver,TronLink.saveKey,"click","back up now");
      ArrayList<String> allTextList = TronLink.getTextList(driver,TronLink.keyIndexText);
      StringBuffer backupMnemonicBf = new StringBuffer(mnemonicText);
      for (String data : allTextList){
        backupMnemonicBf.append(data);
        backupMnemonicBf.append(" ");
      }
      mnemonicText = backupMnemonicBf.toString();
      System.out.println(mnemonicText);
      //confirm mnemonic
      List<MobileElement> confirmElements = driver.findElementsById(TronLink.itemText);
      confirmElements.get(TronLink.getSameMnemonicIdex(driver,allTextList,TronLink.itemText,TronLink.numberIndex)).click();
      TronLink.testOperation(driver,TronLink.nextStepButton,"click","back up now");
      confirmElements = driver.findElementsById(TronLink.itemText);
      confirmElements.get(TronLink.getSameMnemonicIdex(driver,allTextList,TronLink.itemText,TronLink.numberIndex)).click();
      TronLink.testOperation(driver,TronLink.nextStepButton,"click","back up now");

      //tab me
      TronLink.testOperation(driver,TronLink.tabMy,"click","click tab My");
      TronLink.testOperation(driver,TronLink.my_walletManager,"click","click wallet manager");

      //get information
      walletAddress = TronLink.getText(driver,TronLink.addressText);
      TronLink.testOperation(driver,"swipeUp","");

      TronLink.testOperation(driver,TronLink.backupPrivateKey,"click","click backup PrivateKey");
      TronLink.testOperation(driver,TronLink.passWord,"input","Test0001","input password");
      TronLink.testOperation(driver,TronLink.riskBackup,"click","click ok");
      TronLink.testOperation(driver,TronLink.done,"click","click done");
      driver.pressKey(new KeyEvent(AndroidKey.BACK));
      TronLink.testOperation(driver,TronLink.tabAssets,"click","click tab assets");
    }
    catch (Exception ex) {
      System.out.print(ex);
      return null;
    }
    return driver;
  }



  private static Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>() {
    private static final long serialVersionUID = 1L;

    {
      put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
      put(EncodeHintType.CHARACTER_SET, "utf-8");
      put(EncodeHintType.MARGIN, 0);
    }
  };

  public static void QRCode(String content, String imgUrl, String name) {
    try {
      File codeFile = new File(imgUrl);
      MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
      BitMatrix bm = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
      BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
      for (int x = 0; x < WIDTH; x++) {
        for (int y = 0; y < HEIGHT; y++) {
          image.setRGB(x, y, bm.get(x, y) ? QRCOLOR : BGWHITE);
        }
      }
      image.flush();
      ImageIO.write(image, "png", codeFile);
      Runtime.getRuntime().exec("adb push "+imgUrl+" storage/sdcard0/tronlink/111" + name +".png");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void clickPicture(AndroidDriver driver,String pictureName){
    systemAllow(driver);
    MobileElement imageView = (MobileElement) driver.findElementByClassName(TronLink.imageView);
    imageView.click();
    TronLink.testOperation(driver,language_title,"click","");
    systemAllow(driver);
    List<MobileElement> name = driver.findElementsById(TronLink.pictureName);
    for (MobileElement data : name){
      System.out.println(data.getText());
      if (data.getText().indexOf(pictureName)!=-1){
        data.click();
        break;
      }
    }
  }

  public static void systemAllow(AndroidDriver driver){
    try{
      MobileElement element = (MobileElement) driver.findElement(new MobileBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.Button\").textMatches(\".*ALLOW.*\")"));
      if (element.isDisplayed()){
        element.click();
      }
    } catch (Exception e){
      System.out.println(e);
    }
  }
}
