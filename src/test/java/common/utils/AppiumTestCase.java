package common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;


import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import javax.imageio.ImageIO;


public class AppiumTestCase {
    private  static final int QRCOLOR = 0xFF000000;
    private static  final int BGWHITE = 0xFFFFFFFF;
    private static  final int WIDTH = 400;
    private  static final int HEIGHT = 400;
    public static String adb = "/Users/tron/Library/Android/sdk/platform-tools/adb";
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
    public static String balance = "com.tronlink.wallet:id/tv_balance";
    public static String walletBalance = "com.tronlink.wallet:id/tv_blance";
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
    public static String trc20TransferOperationInAddPermissionXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/android.widget.TextView";
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
    public static String setting_dapp_change = "com.tronlink.wallet:id/ll_select";
    public static String common_left = "com.tronlink.wallet:id/ll_common_left";
    public static String assetsList = "com.tronlink.wallet:id/rl_main";
    public static String withdrawButton = "com.tronlink.wallet:id/bt_go";

    public static String language_title = "com.tronlink.wallet:id/title";
    public static String moneyValue = "com.tronlink.wallet:id/tv_money_value";
    public static String mnemonicTool = "com.tronlink.wallet:id/et_innertitle";
    public static String oneClickConvert = "com.tronlink.wallet:id/bt_convert";
    public static String nodeShastText = "com.tronlink.wallet:id/tv_node_shast";
    public static String manageropen = "com.tronlink.wallet:id/manageropen";
    public static String selectWallet = "com.tronlink.wallet:id/rl_selected";
    public static String pictureName = "android:id/title";
    public static String imageView = "android.widget.ImageView";
    public static String deposit = "com.tronlink.wallet:id/ll_deposit";

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


    protected static AndroidDriver driver;
    protected DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    //protected int port = 4723;

    protected ReentrantLock serverLock = new ReentrantLock();


    @Parameters({"port","platformName", "platformVersion", "deviceName","udid","bootstrap_port"})
    @BeforeSuite
    public void startServer(String port, String platformName, String platformVersion, String deviceName,String udid,String bootstrap_port) {
        boolean needStartServer = true;
        if (needStartServer) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(port+udid);
                        Process process = Runtime.getRuntime().exec("appium -a 127.0.0.1 -p "+port + " -u " + udid + " -bp " + bootstrap_port);
                        InputStreamReader isr=new InputStreamReader(process.getInputStream());
                        Scanner sc=new Scanner(isr);
                        StringBuffer sb = new StringBuffer();
                        sb.append(sc.next());
                        System.out.println(sb.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Parameters({"port","platformName", "platformVersion", "deviceName","udid","bootstrap_port"})
    @BeforeTest()
    public void setUp(String port, String platformName, String platformVersion, String deviceName,String udid,String bootstrap_port)throws MalformedURLException{
        System.out.println(port);
        String url = "http://127.0.0.1:"+port+"/wd/hub";
        desiredCapabilities.setCapability("deviceName", deviceName);
        desiredCapabilities.setCapability("platformName", platformName);
        desiredCapabilities.setCapability("platformVersion", platformVersion);
        desiredCapabilities.setCapability("udid", udid);
        desiredCapabilities.setCapability("app", "/Users/tron/Desktop/app-tronTest-release.apk");
        URL remoteUrl = new URL(url);
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @Parameters({"suitName", "port"})
    @AfterSuite
    public void stopServer(String suitName, String port) {
        System.out.println("AfterSuite, stopServer() " + suitName);
    }


    protected void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static  String cmdReturn(String cmd) throws IOException {
        Process process = Runtime.getRuntime().exec(cmd);
        InputStreamReader isr=new InputStreamReader(process.getInputStream());
        Scanner sc=new Scanner(isr);
        StringBuffer sb = new StringBuffer();
        while(sc.hasNext()){
            sb.append(sc.next());
        }
        return sb.toString();
    }

    public static ArrayList<String> devicesReturn(String cmd) throws IOException{
        Process process = Runtime.getRuntime().exec(cmd);
        InputStreamReader isr=new InputStreamReader(process.getInputStream());
        Scanner sc=new Scanner(isr);
        ArrayList<String> al = new ArrayList<>();
        while(sc.hasNext()){
            al.add(sc.next());
        }
        Iterator<String> iterator = al.iterator();
        while (iterator.hasNext()){
            String s = iterator.next();
            if (s.equals("List")||s.equals("of")||s.equals("devices")||s.equals("attached")||s.equals("device")){
                iterator.remove();
            }
        }
        System.out.println(al);
        return al;
    }

    public static void waitTargetElementAppear() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return;
    }


    //click action
    public static void testOperation(String resId, String step ,String description) {
        testOperation(resId, step, "",description);
    }
    //swipe action
    public static void testOperation(String step, String description) {
        testOperation("", step, "",description);
    }

    public static void testOperation(String resId, String action, String input, String description) {
//    getScreenshot( description);
        waitTargetElementAppear();
        MobileElement element = null;
        if (!resId.isEmpty()) {
            if (resId.indexOf("com.tronlink.wallet:id") != -1){
                driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                element = (MobileElement) driver.findElementById(resId);
            }else if (resId.indexOf(".png") != -1){
                element = (MobileElement) driver.findElementByImage(getReferenceImageB64(resId));
            }else {
                element = (MobileElement) driver.findElement(MobileBy.xpath(resId));

            }
        }
        switch (action) {
            case "click":
                element.click();
                break;
            case "input":
                element.setValue(input);
//        if (input.equals(testPassword)) {
//          driver.navigate().back();
//        } else {
//          driver.hideKeyboard();
//        }

                break;
            case "swipeUp":
                swipeUp();
                break;
            case "swipeDown":
                swipeDown();
                break;
            case "swipeRight":
                swipeRight();
                break;
            case "swipeLeft":
                swipeLeft();
                break;
        }
    }

    public static   void swipeUp(){
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

    public  static void swipeDown(){
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

    public  static void swipeRight(){
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

    public  static void swipeLeft(){
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
    public static  void getScreenshot(String description){
        waitTargetElementAppear();
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

    public static   boolean isElement(String element){
        String pageSource = driver.getPageSource();
        System.out.println(pageSource);
        if (pageSource.indexOf(element) == -1){
            return false;
        }else{
            return true;
        }
    }

    public static  boolean isEnabled(String element){
        return driver.findElementById(element).isEnabled();
    }

    public   boolean isDisplayed(String element){
        return driver.findElementById(element).isDisplayed();
    }

    public   ArrayList<String> getTextList(String element){
        List<MobileElement> text = driver.findElementsById(element);
        ArrayList<String> TextList = new ArrayList<>();
        for (MobileElement data : text){
            TextList.add(data.getText());
        }
        return TextList;
    }


    public  String getText(String elementIdOrXPath){
        waitTargetElementAppear();
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

    public   int getSameMnemonicIdex(ArrayList<String> allTextList,String confirmItem,String numberIndex){
        List<String> confirmList = getTextList(confirmItem);
        int number = 0;
        if (confirmList.size() > 6){
            List<MobileElement> numberList = driver.findElementsById(numberIndex);
            number = Integer.parseInt(numberList.get(1).getText().substring(1)) - 1;
            confirmList = getTextList(confirmItem).subList(6,12);
        }else {
            number = Integer.parseInt(driver.findElementById(numberIndex).getText().substring(1)) - 1;
        }
        int flag = confirmList.indexOf(allTextList.get(number));
        return flag;
    }

    public   void screenOn() {
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

    private static  Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>() {
        private   final long serialVersionUID = 1L;

        {
            put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            put(EncodeHintType.CHARACTER_SET, "utf-8");
            put(EncodeHintType.MARGIN, 0);
        }
    };



    public static  void systemAllow(){
        try{
            MobileElement element = (MobileElement) driver.findElement(new MobileBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.Button\").textMatches(\".*(?:ALLOW|允许|OK)\")"));
            if (element.isDisplayed()){
                element.click();
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public static  String getReferenceImageB64(String image){
        String elementsFile = null;
        File refImgFile = new File(image);
        try {
            elementsFile = Base64.getEncoder().encodeToString(Files.readAllBytes(refImgFile.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return elementsFile;
    }

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
            Runtime.getRuntime().exec(adb +" push "+imgUrl+" storage/sdcard0/tronlink/111" + name +".png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clickPicture(String pictureName){
        systemAllow();
        MobileElement imageView1 = (MobileElement) driver.findElementByClassName(imageView);
        imageView1.click();
        testOperation(language_title,"click","");
        systemAllow();
        List<MobileElement> name = driver.findElementsById( pictureName);
        for (MobileElement data : name){
            System.out.println(data.getText());
            if (data.getText().indexOf(pictureName)!=-1){
                data.click();
                break;
            }
        }
    }

    public static void importWallet(String walletPrivateKey) {
            //startup page
            testOperation(   importAccountId,"click","click import Account");
            while (! isEnabled(  acceptImportAccount)){
                testOperation( "swipeUp","");
            }
            testOperation(  acceptImportAccount,"click","click Accept");

            //use Private Key import account
            testOperation(  privateKey,"click","click Private key");
            testOperation(  enterContent,"input",walletPrivateKey,"enter private key");
            testOperation(  nextStep,"click","click Next step");
            Date date = new Date();
            String timestamp = String.valueOf(date.getTime());
            testOperation(  setUpName,"input","Test_"+timestamp,"input name");
            testOperation(  creatNextStep,"click","1:input name");
            testOperation(  passWord,"input","Test0001","input password");
            testOperation(  creatNextStep2,"click","2:click next step");
            testOperation(  passWord,"input","Test0001","input password again");
            testOperation(  creatNextStep3,"click","3:click carry out");
    }
}

