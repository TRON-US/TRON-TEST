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
import io.appium.java_client.remote.AndroidMobileCapabilityType;
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
    private   final int QRCOLOR = 0xFF000000;
    private   final int BGWHITE = 0xFFFFFFFF;
    private   final int WIDTH = 400;
    private   final int HEIGHT = 400;
    public  String adb = "/Users/tron/Library/Android/sdk/platform-tools/adb";
    public  String mnemonicText = "";
    public  String walletAddress = "";
    public  String walletPrivateKey = "";
    public  String importAccountId = "com.tronlink.wallet:id/tv_import";
    public  String createAccountId = "com.tronlink.wallet:id/tv_create";
    public  String addWallet = "com.tronlink.wallet:id/tv_create";
    public  String sendCoinId = "com.tronlink.wallet:id/rl_send";
    public  String receiveCoinId = "com.tronlink.wallet:id/rl_receive";
    public  String receiveScreenSameQRCodeId = "com.tronlink.wallet:id/tv_common_right2";
    public  String receiveScreenAddressTextId = "com.tronlink.wallet:id/address";
    public  String copyAddressIconId = "com.tronlink.wallet:id/copy";
    public  String tronLendingId = "com.tronlink.wallet:id/rl_energy_lease";
    public  String voteId = "com.tronlink.wallet:id/rl_vote";
    public  String voteResetId = "com.tronlink.wallet:id/reset";
    public  String voteSlectionInputId = "com.tronlink.wallet:id/et_input";
    public  String voteInputQuantityXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[3]/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.EditText";
    public  Long voteQuantity = 2L;
    public  String voteNowId = "com.tronlink.wallet:id/bt_go";
    public  String addAssetId = "com.tronlink.wallet:id/rl_add_assets";
    public  String enter_NameIdContractAddress_InputBox_id = "com.tronlink.wallet:id/et_search";
    public  String energyOptionIconId = "com.tronlink.wallet:id/tv_energy";
    public  String bandwidthOptionIconId = "com.tronlink.wallet:id/tv_bandwidth";
    public  String bandwidthQuestionId = "com.tronlink.wallet:id/bandwidth_question";
    public  String energyQuestionId = "com.tronlink.wallet:id/bandwidth_question";
    public  String bandwidthQuestionContentId = "com.tronlink.wallet:id/content";
    public  String energyQuestionContentId = "com.tronlink.wallet:id/content";
    public  String balanceInFrozenScreenId = "com.tronlink.wallet:id/current_use";
    public  String trxValueInAssetScreenId = "com.tronlink.wallet:id/tv_trx_value";
    public  String frozenQuantityInputId = "com.tronlink.wallet:id/et_freeze_count";
    public  String freeezeUnfreezeId = "com.tronlink.wallet:id/rl_freeze_unfreeze";
    public  String sendCoinAmountId = "com.tronlink.wallet:id/et_count";
    public  Long sendCoinAmount = 1L;
    public  Long frozenQuantityForBandwidth = 2L;
    public  Long frozenQuantityForEnergy = 3L;
    public  Long assetIdOfQuery = 1000001L;
    public  String balance = "com.tronlink.wallet:id/tv_balance";
    public  String walletBalance = "com.tronlink.wallet:id/tv_blance";
    public  String freezeIconId = "com.tronlink.wallet:id/freeze";
    public  String freezeNowIconId = "com.tronlink.wallet:id/bt_go";
    public  String freezeRuleId = "com.tronlink.wallet:id/tv_common_right2";
    public  String freezeDoc1Id = "com.tronlink.wallet:id/doc0_spe";
    public  String freezeEnergyDetailId = "com.tronlink.wallet:id/ll_energy_arrow";
    public  String freezeBandwidthDetailId = "com.tronlink.wallet:id/ll_bandwidth_arrow";
    public  String myFreezeEnergyAmountId = "com.tronlink.wallet:id/tv_myfreeze";
    public  String myFreezeBandwidthAmountid = "com.tronlink.wallet:id/tv_myfreeze_bandwidth";
    public  String otherFreezeBandwidthAmountId = "com.tronlink.wallet:id/tv_otherfreeze_bandwidth";
    public  String otherFreezeEnergyAmountId = "com.tronlink.wallet:id/tv_otherfreeze";
    public  String totalFreezeEnergyAmountId = "com.tronlink.wallet:id/tv_totalfreeze";
    public  String totalFreezeBandwidthAmountId = "com.tronlink.wallet:id/tv_totalfreeze_bandwidth";
    public  String votingPowerInFreezeId = "com.tronlink.wallet:id/tv_voting_power";
    public  String sendCoinButtonId = "com.tronlink.wallet:id/send";
    public  String transferNowId = "com.tronlink.wallet:id/bt_go";
    public  String transactionConfirmButtonId = "com.tronlink.wallet:id/bt_send";
    public  String transactionConfirmInputPasswordId = "com.tronlink.wallet:id/et_new_password";
    public  String receiveAddressId = "com.tronlink.wallet:id/et_address";
    public  String acceptImportAccount = "com.tronlink.wallet:id/bt_accept";
    public  String assetsCount = "com.tronlink.wallet:id/assets_count";
    public  String assetIconId = "com.tronlink.wallet:id/assets";
    public  String assetDisplayAreaId = "com.tronlink.wallet:id/rl_inner";
    public  String assetSwitchId = "com.tronlink.wallet:id/iv_switch";
    public  String assetsDisplayedXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout[2]";
    public  String assetsDisplayedFirstElementXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.support.v4.view.ViewPager/android.widget.RelativeLayout/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.RelativeLayout";
    public  String marketsIconId = "com.tronlink.wallet:id/appmarket";
    public  String priceChangeId = "com.tronlink.wallet:id/tv_change";
    public  String lastestPriceXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.TextView";
    public  String firstPriceOfPriceChangeXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.TextView";
    public  String firstPriceOfLastestPriceXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.TextView[1]";
    public  String marketsExchangeListId = "com.tronlink.wallet:id/rl_list";
    public  String marketsSearchId = "com.tronlink.wallet:id/iv_search";
    public  String marketsSearchInputId = "com.tronlink.wallet:id/et_search";
    public  String firstExchangeInMarketsSearchScreenXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.TextView[1]";
    public  String discoverIconId = "com.tronlink.wallet:id/app1";
    public  String discoverSearchEnterId = "com.tronlink.wallet:id/iv_one";
    public  String discoverSearchInputId = "com.tronlink.wallet:id/et_search";
    public  String discoverSearchConfirmId = "com.tronlink.wallet:id/tv_search";
    public  String discoverSearchResultNameId = "com.tronlink.wallet:id/tv_name";
    public  String discoverSearchresultDescriptionId = "com.tronlink.wallet:id/tv_description";
    public  String discoverSearchFirstResultXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]";
    public  String enterConfirmIconOfFirstDiscoverSearchId = "com.tronlink.wallet:id/tv_ok";
    public  String enterCancelIconOfFirstDiscoverSearchId = "com.tronlink.wallet:id/tv_cancle";
    public  String discoverSearchHistoryId = "com.tronlink.wallet:id/imageview";
    public  String discoverSearchScanId = "com.tronlink.wallet:id/iv_scan";
    public  String meIconId = "com.tronlink.wallet:id/my";
    public  String meFriendInvitationId = "com.tronlink.wallet:id/tv_friend_invitation";
    public  String meAnnouncementId = "com.tronlink.wallet:id/tv_an";
    public  String meJoinOurCommunitiesId = "com.tronlink.wallet:id/join_community";
    public  String meJoinOurCommunitiesEnglishTelegraphGroupId = "com.tronlink.wallet:id/rl_en_arrow";
    public  String meJoinOurCommunitiesChineseTelegraphGroupId = "com.tronlink.wallet:id/rl_zh_arrow";
    public  String meJoinOurCommunitiesTwitterId = "com.tronlink.wallet:id/rl_twitter_arrow";
    public  String meJoinOurCommunitiesWechatId = "com.tronlink.wallet:id/rl_twitter_arrow";
    public  String meHelpCenterId = "com.tronlink.wallet:id/help";
    public  String meAboutUsId = "com.tronlink.wallet:id/about";
    public  String meAboutUsVersionLogsId = "com.tronlink.wallet:id/log";
    public  String meAbountUsVersionVersionUpdateId = "com.tronlink.wallet:id/update";
    public  String mutliSignatureManagementId = "com.tronlink.wallet:id/rl_sign_manager";
    public  String mutliSignQuestionId = "com.tronlink.wallet:id/iv_qr";
    public  String mutliSignQuestionContentXPath = "/hierarchy/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.TextView";
    public  String mutliSignAddPermissionId = "com.tronlink.wallet:id/bt_go";
    public  String activeScreenMoreId = "com.tronlink.wallet:id/ll_more";
    public  String modifyPermissionsId = "com.tronlink.wallet:id/ll_edit";
    public  String deletePermissionId = "com.tronlink.wallet:id/ll_delete";
    public  String deletePermissionConfirmId = "com.tronlink.wallet:id/tv_ok";
    public  String deletePermissionCancelId = "com.tronlink.wallet:id/tv_cancle";
    public  String permissionOperationId = "com.tronlink.wallet:id/fl_operations";
    public  String addPermissionId = "com.tronlink.wallet:id/bt_go";
    public  String permissionNameInputId = "com.tronlink.wallet:id/et_permission_name";
    public  String trxTransferOperationInAddPermissionXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.TextView";
    public  String trc10TransferOperationInAddPermissionXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.TextView";
    public  String trc20TransferOperationInAddPermissionXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/android.widget.TextView";
    public  String thresholdInAddPermissionInputId = "com.tronlink.wallet:id/et_threshold";
    public  String permissionAddressInputId = "com.tronlink.wallet:id/et_key_address";
    public  String permissionWeightInputId = "com.tronlink.wallet:id/et_weight";
    public  String addKeyInAddPermissionId = "com.tronlink.wallet:id/rl_add_key_button";
    public  String secondPermissionAddressInputXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout[5]/android.widget.LinearLayout[2]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.EditText[1]";
    public  String secondPermissionWeightInputXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout[5]/android.widget.LinearLayout[2]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.EditText[2]";
    public  String addPermissionConfirmId = "com.tronlink.wallet:id/tv_confirm";
    public  String updateAccountPermissionServiceChargeId = "com.tronlink.wallet:id/tv_trx_amount";
    public  String updateAccountPermissionInputPasswordId = "com.tronlink.wallet:id/et_new_password";
    public  String updateAccountPermissionPayIconId = "com.tronlink.wallet:id/bt_send";

    public  String transactionHistoryId = "com.tronlink.wallet:id/transfer_history";
    public  String transactionHistoryQueryWalletId = "com.tronlink.wallet:id/iv_qr";
    public  String transactionHistoryWalletResultNameId = "com.tronlink.wallet:id/tv_name";
    public  String transactionHistoryReceiveXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab[2]/android.widget.TextView";
    public  String transactionHistorySentXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab[3]/android.widget.TextView";
    public  String transactionHistoryAllXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab[1]/android.widget.TextView";
    public  String transactionHistorySentFirstResultConfirmedXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab[1]/android.widget.TextView";
    public  String transactionHistorySentFirstResultAddressXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.support.v4.view.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.TextView[1]";
    public  String transactionHistorySentFirstResultContractTypeXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.support.v4.view.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.TextView[2]";
    public  String transactionHistorySentFirstResultAmountXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.support.v4.view.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView[1]";
    public  String transactionHistorySentFirstResultDateXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.support.v4.view.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.RelativeLayout/android.widget.TextView";
    public  String transactionHistoryReceivedFirstResultAddressXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.support.v4.view.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView[2]";
    public  String privateKey = "com.tronlink.wallet:id/cd_pk";
    public  String enterContent = "com.tronlink.wallet:id/et_content";
    public  String nextStep = "com.tronlink.wallet:id/bt_next";
    public  String qrButton = "com.tronlink.wallet:id/iv_qr";
    public  String systemAllow = "com.android.packageinstaller:id/permission_allow_button";
    public  String mnemonic = "com.tronlink.wallet:id/rl_mm";
    public  String hdWallet = "com.tronlink.wallet:id/ll_hd_wallet";
    public  String keystore = "com.tronlink.wallet:id/cd_kt";
    public  String watchWallet = "com.tronlink.wallet:id/cd_ow";
    public  String createWallet = "com.tronlink.wallet:id/cd_cw";
    public  String setUpName = "com.tronlink.wallet:id/et_name";
    public  String creatNextStep = "com.tronlink.wallet:id/creat";
    public  String passWord = "com.tronlink.wallet:id/et_password";
    public  String creatNextStep2 = "com.tronlink.wallet:id/creat";
    public  String creatNextStep3 = "com.tronlink.wallet:id/creat";
    public  String riskIgnore = "com.tronlink.wallet:id/tv_cancle";
    public  String riskBackup = "com.tronlink.wallet:id/tv_ok";
    public  String addressText = "com.tronlink.wallet:id/tv_address";
    public  String walletPassword = "com.tronlink.wallet:id/et_password";
    public  String confirm = "com.tronlink.wallet:id/tv_ok";
    public  String privateKeyText = "com.tronlink.wallet:id/tv_privatekey";
    public  String keystoreText = "com.tronlink.wallet:id/tv_keystore";
    public  String done = "com.tronlink.wallet:id/backup";
    public  String backUpNow = "com.tronlink.wallet:id/backup";
    public  String gotItButton = "com.tronlink.wallet:id/bt_know";
    public  String saveKey = "com.tronlink.wallet:id/save";
    public  String keyIndexText = "com.tronlink.wallet:id/text";
    public  String itemText = "com.tronlink.wallet:id/tv_item";
    public  String numberIndex = "com.tronlink.wallet:id/tv_number";
    public  String nextStepButton = "com.tronlink.wallet:id/tv_next";
    public  String totalAssets = "com.tronlink.wallet:id/tv_total_assets";
    public  String dappUrl = "com.tronlink.wallet:id/et_url";
    public  String dappButton = "com.tronlink.wallet:id/bt";

    public  String tabAssets = "com.tronlink.wallet:id/assets";
    public  String tabAppmarket = "com.tronlink.wallet:id/appmarket";
    public  String tabApp1 = "com.tronlink.wallet:id/app1";
    public  String tabMy = "com.tronlink.wallet:id/my";
    public  String settings = "com.tronlink.wallet:id/setting";
    public  String setting_languane = "com.tronlink.wallet:id/languane";
    public  String setting_currency = "com.tronlink.wallet:id/money";
    public  String setting_node = "com.tronlink.wallet:id/node";
    public  String setting_developer = "com.tronlink.wallet:id/testnode";
    public  String setting_conversion = "com.tronlink.wallet:id/convert";
    public  String setting_dapp = "com.tronlink.wallet:id/dapp";
    public  String setting_dapp_change = "com.tronlink.wallet:id/ll_select";
    public  String common_left = "com.tronlink.wallet:id/ll_common_left";
    public  String assetsList = "com.tronlink.wallet:id/rl_main";
    public  String withdrawButton = "com.tronlink.wallet:id/bt_go";

    public  String language_title = "com.tronlink.wallet:id/title";
    public  String moneyValue = "com.tronlink.wallet:id/tv_money_value";
    public  String mnemonicTool = "com.tronlink.wallet:id/et_innertitle";
    public  String oneClickConvert = "com.tronlink.wallet:id/bt_convert";
    public  String nodeShastText = "com.tronlink.wallet:id/tv_node_shast";
    public  String manageropen = "com.tronlink.wallet:id/manageropen";
    public  String selectWallet = "com.tronlink.wallet:id/rl_selected";
    public  String pictureName = "android:id/title";
    public  String imageView = "android.widget.ImageView";
    public  String deposit = "com.tronlink.wallet:id/ll_deposit";

    public  String my_walletManager = "com.tronlink.wallet:id/wallet_manager";
    public  String deleteWallet = "com.tronlink.wallet:id/delete";
    public  String deleteWallet2 = "com.tronlink.wallet:id/delete2";
    public  String deleteWalletTop = "com.tronlink.wallet:id/delete_top";
    public  String etPassword = "com.tronlink.wallet:id/et_password";
    public  String backupMnemonic = "com.tronlink.wallet:id/rl_mnemonic";
    public  String backupPrivateKey = "com.tronlink.wallet:id/rl_privatekey";
    public  String backupKeystore = "com.tronlink.wallet:id/rl_keystore";
    public  String testPrivateKey = "ecd4bbba178b1b0d2a0c1e6e9108e0cab805a2c1365c42c9eafaff104dbf1e72";
    public  String receiverAddress = "TG5wFVvrJiTkBA1WaZN3pzyJDfkgHMnFrp";
    public  String ownerAddress = "TMNQnpTsNHuK1NwqMf6WTBydXvNsv9p6of";
    public  String activePermissionManager1Address = "THph9K2M2nLvkianrMGswRhz5hjSA9fuH7";
    public  String activePermissionManager2Address = "TV75jZpdmP2juMe1dRwGrwpV6AMU6mr1EU";
    public  String testPassword = "Test0001";


    protected AndroidDriver driver;
    protected DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

    @BeforeSuite
    public void setupDevices(){

    }

    @Parameters({"port","platformName", "platformVersion", "deviceName","udid"})
    @BeforeTest
    public void startServer(String port, String platformName, String platformVersion, String deviceName,String udid) {
                    try {
                        System.out.println(port+udid);
                        Process process = Runtime.getRuntime().exec("appium -a 127.0.0.1 -p "+port + " -u " + udid);
                        InputStreamReader isr=new InputStreamReader(process.getInputStream());
                        Scanner sc=new Scanner(isr);
                        StringBuffer sb = new StringBuffer();
                        sb.append(sc.next());
                        System.out.println(sb.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
    }

    @Parameters({"port","platformName", "platformVersion", "deviceName","udid","systemPort"})
    @BeforeClass()
    public void setUp(String port, String platformName, String platformVersion, String deviceName,String udid,String systemPort)throws MalformedURLException{
        System.out.println(port);
        String url = "http://127.0.0.1:"+port+"/wd/hub";
        desiredCapabilities.setCapability("deviceName", deviceName);
        desiredCapabilities.setCapability("platformName", platformName);
        desiredCapabilities.setCapability("platformVersion", platformVersion);
        desiredCapabilities.setCapability("unicodeKeyboard", true);
        desiredCapabilities.setCapability("udid", udid);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.AVD_LAUNCH_TIMEOUT, 300000);
        desiredCapabilities.setCapability("systemPort", systemPort);
        desiredCapabilities.setCapability("noSign", true);
        File appDir = new File(System.getProperty("user.dir"), ".//");
        File app = new File(appDir, "TronLink.apk");
        desiredCapabilities.setCapability("app", app.getAbsolutePath());
        URL remoteUrl = new URL(url);
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    public  static String cmdReturn(String cmd) throws IOException {
        Process process = Runtime.getRuntime().exec(cmd);
        InputStreamReader isr=new InputStreamReader(process.getInputStream());
        Scanner sc=new Scanner(isr);
        StringBuffer sb = new StringBuffer();
        while(sc.hasNext()){
            sb.append(sc.next());
        }
        return sb.toString();
    }

    public  static List<String> getDeviceList(String cmd) throws IOException {
        Process process = Runtime.getRuntime().exec(cmd);
        InputStreamReader isr=new InputStreamReader(process.getInputStream());
        Scanner s=new Scanner(isr);
        List<String> deviceNamesList = new ArrayList<>();
        String str;
        while(s.hasNext()){
            str = s.next();
            if (str.equals("List")||str.equals("of")||str.equals("devices")||str.equals("attached")||str.equals("device")){
                continue;
            }
            deviceNamesList.add(str);
        }
        return deviceNamesList;
    }

    public  static ArrayList<String> devicesReturn(String cmd) throws IOException{
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

    public  void waitTargetElementAppear() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return;
    }


    //click action
    public  void testOperation(String resId, String step ,String description) {
        testOperation(resId, step, "",description);
    }
    //swipe action
    public  void testOperation(String step, String description) {
        testOperation("", step, "",description);
    }

    public  void testOperation(String resId, String action, String input, String description) {
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

    public    void swipeUp(){
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

    public   void swipeDown(){
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

    public   void swipeRight(){
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

    public   void swipeLeft(){
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
    public   void getScreenshot(String description){
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

    public    boolean isElement(String element){
        String pageSource = driver.getPageSource();
        System.out.println(pageSource);
        if (pageSource.indexOf(element) == -1){
            return false;
        }else{
            return true;
        }
    }

    public   boolean isEnabled(String element){
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

    private   Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>() {
        private   final long serialVersionUID = 1L;

        {
            put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            put(EncodeHintType.CHARACTER_SET, "utf-8");
            put(EncodeHintType.MARGIN, 0);
        }
    };



    public   void systemAllow(){
        try{
            MobileElement element = (MobileElement) driver.findElement(new MobileBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.Button\").textMatches(\".*(?:ALLOW|允许|OK)\")"));
            if (element.isDisplayed()){
                element.click();
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public   String getReferenceImageB64(String image){
        String elementsFile = null;
        File refImgFile = new File(image);
        try {
            elementsFile = Base64.getEncoder().encodeToString(Files.readAllBytes(refImgFile.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return elementsFile;
    }

    public  void QRCode(String content, String imgUrl, String name) {
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

    public  void clickPicture(String pictureName){
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

    public  void importWallet(String walletPrivateKey) {
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

