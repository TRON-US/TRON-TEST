package android.com.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
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
    public  String importAccountId = "wallet.tronlink.harmony:id/tv_import";
    public  String createAccountId = "wallet.tronlink.harmony:id/tv_create";
    public  String addWallet = "wallet.tronlink.harmony:id/tv_create";
    public  String sendCoinId = "wallet.tronlink.harmony:id/rl_send";
    public  String receiveCoinId = "wallet.tronlink.harmony:id/rl_receive";
    public  String receiveScreenSameQRCodeId = "wallet.tronlink.harmony:id/tv_common_right2";
    public  String receiveScreenAddressTextId = "wallet.tronlink.harmony:id/address";
    public  String copyAddressIconId = "wallet.tronlink.harmony:id/copy";
    public  String tronLendingId = "wallet.tronlink.harmony:id/rl_energy_lease";
    public  String voteId = "wallet.tronlink.harmony:id/rl_vote";
    public  String voteResetId = "wallet.tronlink.harmony:id/reset";
    public  String voteSlectionInputId = "wallet.tronlink.harmony:id/et_input";
    public  String voteInputQuantityXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[3]/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.EditText";
    public  Long voteQuantity = 2L;
    public  String voteNowId = "wallet.tronlink.harmony:id/bt_go";
    public  String addAssetId = "wallet.tronlink.harmony:id/rl_add_assets";
    public  String enter_NameIdContractAddress_InputBox_id = "wallet.tronlink.harmony:id/et_search";
    public  String energyOptionIconId = "wallet.tronlink.harmony:id/tv_energy";
    public  String bandwidthOptionIconId = "wallet.tronlink.harmony:id/tv_bandwidth";
    public  String bandwidthQuestionId = "wallet.tronlink.harmony:id/bandwidth_question";
    public  String energyQuestionId = "wallet.tronlink.harmony:id/bandwidth_question";
    public  String bandwidthQuestionContentId = "wallet.tronlink.harmony:id/content";
    public  String energyQuestionContentId = "wallet.tronlink.harmony:id/content";
    public  String balanceInFrozenScreenId = "wallet.tronlink.harmony:id/current_use";
    public  String trxValueInAssetScreenId = "wallet.tronlink.harmony:id/tv_trx_value";
    public  String frozenQuantityInputId = "wallet.tronlink.harmony:id/et_freeze_count";
    public  String freeezeUnfreezeId = "wallet.tronlink.harmony:id/rl_freeze_unfreeze";
    public  String sendCoinAmountId = "wallet.tronlink.harmony:id/et_count";
    public  Long sendCoinAmount = 1L;
    public  Long frozenQuantityForBandwidth = 2L;
    public  Long frozenQuantityForEnergy = 3L;
    public  Long assetIdOfQuery = 1000001L;
    public  String balance = "wallet.tronlink.harmony:id/tv_balance";
    public  String walletBalance = "wallet.tronlink.harmony:id/tv_blance";
    public  String freezeIconId = "wallet.tronlink.harmony:id/freeze";
    public  String freezeNowIconId = "wallet.tronlink.harmony:id/bt_go";
    public  String freezeRuleId = "wallet.tronlink.harmony:id/tv_common_right2";
    public  String freezeDoc1Id = "wallet.tronlink.harmony:id/doc0_spe";
    public  String freezeEnergyDetailId = "wallet.tronlink.harmony:id/ll_energy_arrow";
    public  String freezeBandwidthDetailId = "wallet.tronlink.harmony:id/ll_bandwidth_arrow";
    public  String myFreezeEnergyAmountId = "wallet.tronlink.harmony:id/tv_myfreeze";
    public  String myFreezeBandwidthAmountid = "wallet.tronlink.harmony:id/tv_myfreeze_bandwidth";
    public  String otherFreezeBandwidthAmountId = "wallet.tronlink.harmony:id/tv_otherfreeze_bandwidth";
    public  String otherFreezeEnergyAmountId = "wallet.tronlink.harmony:id/tv_otherfreeze";
    public  String totalFreezeEnergyAmountId = "wallet.tronlink.harmony:id/tv_totalfreeze";
    public  String totalFreezeBandwidthAmountId = "wallet.tronlink.harmony:id/tv_totalfreeze_bandwidth";
    public  String votingPowerInFreezeId = "wallet.tronlink.harmony:id/tv_voting_power";
    public  String sendCoinButtonId = "wallet.tronlink.harmony:id/send";
    public  String transferNowId = "wallet.tronlink.harmony:id/bt_go";
    public  String transactionConfirmButtonId = "wallet.tronlink.harmony:id/bt_send";
    public  String transactionConfirmInputPasswordId = "wallet.tronlink.harmony:id/et_new_password";
    public  String receiveAddressId = "wallet.tronlink.harmony:id/et_address";
    public  String acceptImportAccount = "wallet.tronlink.harmony:id/bt_accept";
    public  String assetsCount = "wallet.tronlink.harmony:id/assets_count";
    public  String assetIconId = "wallet.tronlink.harmony:id/assets";
    public  String assetDisplayAreaId = "wallet.tronlink.harmony:id/rl_inner";
    public  String assetSwitchId = "wallet.tronlink.harmony:id/iv_switch";
    public  String assetsDisplayedXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout[2]";
    public  String assetsDisplayedFirstElementXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.support.v4.view.ViewPager/android.widget.RelativeLayout/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.RelativeLayout";
    public  String marketsIconId = "wallet.tronlink.harmony:id/appmarket";
    public  String priceChangeId = "wallet.tronlink.harmony:id/tv_change";
    public  String lastestPriceXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.TextView";
    public  String firstPriceOfPriceChangeXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.TextView";
    public  String firstPriceOfLastestPriceXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.TextView[1]";
    public  String marketsExchangeListId = "wallet.tronlink.harmony:id/rl_list";
    public  String marketsSearchId = "wallet.tronlink.harmony:id/iv_search";
    public  String marketsSearchInputId = "wallet.tronlink.harmony:id/et_search";
    public  String firstExchangeInMarketsSearchScreenXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.TextView[1]";
    public  String discoverIconId = "wallet.tronlink.harmony:id/app1";
    public  String discoverSearchEnterId = "wallet.tronlink.harmony:id/iv_one";
    public  String discoverSearchInputId = "wallet.tronlink.harmony:id/et_search";
    public  String discoverSearchConfirmId = "wallet.tronlink.harmony:id/tv_search";
    public  String discoverSearchResultNameId = "wallet.tronlink.harmony:id/tv_name";
    public  String discoverSearchresultDescriptionId = "wallet.tronlink.harmony:id/tv_description";
    public  String discoverSearchFirstResultXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]";
    public  String enterConfirmIconOfFirstDiscoverSearchId = "wallet.tronlink.harmony:id/tv_ok";
    public  String enterCancelIconOfFirstDiscoverSearchId = "wallet.tronlink.harmony:id/tv_cancle";
    public  String discoverSearchHistoryId = "wallet.tronlink.harmony:id/imageview";
    public  String discoverSearchScanId = "wallet.tronlink.harmony:id/iv_scan";
    public  String meIconId = "wallet.tronlink.harmony:id/my";
    public  String meFriendInvitationId = "wallet.tronlink.harmony:id/tv_friend_invitation";
    public  String meAnnouncementId = "wallet.tronlink.harmony:id/tv_an";
    public  String meJoinOurCommunitiesId = "wallet.tronlink.harmony:id/join_community";
    public  String meJoinOurCommunitiesEnglishTelegraphGroupId = "wallet.tronlink.harmony:id/rl_en_arrow";
    public  String meJoinOurCommunitiesChineseTelegraphGroupId = "wallet.tronlink.harmony:id/rl_zh_arrow";
    public  String meJoinOurCommunitiesTwitterId = "wallet.tronlink.harmony:id/rl_twitter_arrow";
    public  String meJoinOurCommunitiesWechatId = "wallet.tronlink.harmony:id/rl_twitter_arrow";
    public  String meHelpCenterId = "wallet.tronlink.harmony:id/help";
    public  String meAboutUsId = "wallet.tronlink.harmony:id/about";
    public  String meAboutUsVersionLogsId = "wallet.tronlink.harmony:id/log";
    public  String meAbountUsVersionVersionUpdateId = "wallet.tronlink.harmony:id/update";
    public  String mutliSignatureManagementId = "wallet.tronlink.harmony:id/rl_sign_manager";
    public  String mutliSignQuestionId = "wallet.tronlink.harmony:id/iv_qr";
    public  String mutliSignQuestionContentXPath = "/hierarchy/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.TextView";
    public  String mutliSignAddPermissionId = "wallet.tronlink.harmony:id/bt_go";
    public  String activeScreenMoreId = "wallet.tronlink.harmony:id/ll_more";
    public  String modifyPermissionsId = "wallet.tronlink.harmony:id/ll_edit";
    public  String deletePermissionId = "wallet.tronlink.harmony:id/ll_delete";
    public  String deletePermissionConfirmId = "wallet.tronlink.harmony:id/tv_ok";
    public  String deletePermissionCancelId = "wallet.tronlink.harmony:id/tv_cancle";
    public  String permissionOperationId = "wallet.tronlink.harmony:id/fl_operations";
    public  String addPermissionId = "wallet.tronlink.harmony:id/bt_go";
    public  String permissionNameInputId = "wallet.tronlink.harmony:id/et_permission_name";
    public  String trxTransferOperationInAddPermissionXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.TextView";
    public  String trc10TransferOperationInAddPermissionXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.TextView";
    public  String trc20TransferOperationInAddPermissionXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/android.widget.TextView";
    public  String thresholdInAddPermissionInputId = "wallet.tronlink.harmony:id/et_threshold";
    public  String permissionAddressInputId = "wallet.tronlink.harmony:id/et_key_address";
    public  String permissionWeightInputId = "wallet.tronlink.harmony:id/et_weight";
    public  String addKeyInAddPermissionId = "wallet.tronlink.harmony:id/rl_add_key_button";
    public  String secondPermissionAddressInputXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout[5]/android.widget.LinearLayout[2]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.EditText[1]";
    public  String secondPermissionWeightInputXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout[5]/android.widget.LinearLayout[2]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.EditText[2]";
    public  String addPermissionConfirmId = "wallet.tronlink.harmony:id/tv_confirm";
    public  String updateAccountPermissionServiceChargeId = "wallet.tronlink.harmony:id/tv_trx_amount";
    public  String updateAccountPermissionInputPasswordId = "wallet.tronlink.harmony:id/et_new_password";
    public  String updateAccountPermissionPayIconId = "wallet.tronlink.harmony:id/bt_send";

    public  String transactionHistoryId = "wallet.tronlink.harmony:id/transfer_history";
    public  String transactionHistoryQueryWalletId = "wallet.tronlink.harmony:id/iv_qr";
    public  String transactionHistoryWalletResultNameId = "wallet.tronlink.harmony:id/tv_name";
    public  String transactionHistoryReceiveXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab[2]/android.widget.TextView";
    public  String transactionHistorySentXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab[3]/android.widget.TextView";
    public  String transactionHistoryAllXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab[1]/android.widget.TextView";
    public  String transactionHistorySentFirstResultConfirmedXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab[1]/android.widget.TextView";
    public  String transactionHistorySentFirstResultAddressXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.support.v4.view.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.TextView[1]";
    public  String transactionHistorySentFirstResultContractTypeXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.support.v4.view.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.TextView[2]";
    public  String transactionHistorySentFirstResultAmountXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.support.v4.view.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView[1]";
    public  String transactionHistorySentFirstResultDateXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.support.v4.view.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.RelativeLayout/android.widget.TextView";
    public  String transactionHistoryReceivedFirstResultAddressXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.support.v4.view.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView[2]";
    public  String privateKey = "wallet.tronlink.harmony:id/cd_pk";
    public  String enterContent = "wallet.tronlink.harmony:id/et_content";
    public  String nextStep = "wallet.tronlink.harmony:id/bt_next";
    public  String qrButton = "wallet.tronlink.harmony:id/iv_qr";
    public  String systemAllow = "com.android.packageinstaller:id/permission_allow_button";
    public  String mnemonic = "wallet.tronlink.harmony:id/rl_mm";
    public  String hdWallet = "wallet.tronlink.harmony:id/ll_hd_wallet";
    public  String keystore = "wallet.tronlink.harmony:id/cd_kt";
    public  String watchWallet = "wallet.tronlink.harmony:id/cd_ow";
    public  String createWallet = "wallet.tronlink.harmony:id/cd_cw";
    public  String setUpName = "wallet.tronlink.harmony:id/et_name";
    public  String creatNextStep = "wallet.tronlink.harmony:id/creat";
    public  String passWord = "wallet.tronlink.harmony:id/et_password";
    public  String creatNextStep2 = "wallet.tronlink.harmony:id/creat";
    public  String creatNextStep3 = "wallet.tronlink.harmony:id/creat";
    public  String riskIgnore = "wallet.tronlink.harmony:id/tv_cancle";
    public  String riskBackup = "wallet.tronlink.harmony:id/tv_ok";
    public  String addressText = "wallet.tronlink.harmony:id/tv_address";
    public  String walletPassword = "wallet.tronlink.harmony:id/et_password";
    public  String confirm = "wallet.tronlink.harmony:id/tv_ok";
    public  String privateKeyText = "wallet.tronlink.harmony:id/tv_privatekey";
    public  String keystoreText = "wallet.tronlink.harmony:id/tv_keystore";
    public  String done = "wallet.tronlink.harmony:id/backup";
    public  String backUpNow = "wallet.tronlink.harmony:id/backup";
    public  String gotItButton = "wallet.tronlink.harmony:id/bt_know";
    public  String saveKey = "wallet.tronlink.harmony:id/save";
    public  String keyIndexText = "wallet.tronlink.harmony:id/text";
    public  String itemText = "wallet.tronlink.harmony:id/tv_item";
    public  String numberIndex = "wallet.tronlink.harmony:id/tv_number";
    public  String nextStepButton = "wallet.tronlink.harmony:id/tv_next";
    public  String totalAssets = "wallet.tronlink.harmony:id/tv_total_assets";
    public  String dappUrl = "wallet.tronlink.harmony:id/et_url";
    public  String dappButton = "wallet.tronlink.harmony:id/bt";

    public  String tabAssets = "wallet.tronlink.harmony:id/assets";
    public  String tabAppmarket = "wallet.tronlink.harmony:id/appmarket";
    public  String tabApp1 = "wallet.tronlink.harmony:id/app1";
    public  String tabMy = "wallet.tronlink.harmony:id/my";
    public  String settings = "wallet.tronlink.harmony:id/setting";
    public  String setting_languane = "wallet.tronlink.harmony:id/languane";
    public  String setting_currency = "wallet.tronlink.harmony:id/money";
    public  String setting_node = "wallet.tronlink.harmony:id/node";
    public  String setting_developer = "wallet.tronlink.harmony:id/testnode";
    public  String setting_conversion = "wallet.tronlink.harmony:id/convert";
    public  String setting_dapp = "wallet.tronlink.harmony:id/dapp";
    public  String setting_dapp_change = "wallet.tronlink.harmony:id/ll_select";
    public  String common_left = "wallet.tronlink.harmony:id/ll_common_left";
    public  String assetsList = "wallet.tronlink.harmony:id/rl_main";
    public  String withdrawButton = "wallet.tronlink.harmony:id/bt_go";

    public  String language_title = "wallet.tronlink.harmony:id/title";
    public  String moneyValue = "wallet.tronlink.harmony:id/tv_money_value";
    public  String mnemonicTool = "wallet.tronlink.harmony:id/et_innertitle";
    public  String oneClickConvert = "wallet.tronlink.harmony:id/bt_convert";
    public  String nodeShastText = "wallet.tronlink.harmony:id/tv_node_shast";
    public  String manageropen = "wallet.tronlink.harmony:id/manageropen";
    public  String selectWallet = "wallet.tronlink.harmony:id/rl_selected";
    public  String pictureName = "android:id/title";
    public  String imageView = "android.widget.ImageView";
    public  String deposit = "wallet.tronlink.harmony:id/ll_deposit";

    public  String my_walletManager = "wallet.tronlink.harmony:id/wallet_manager";
    public  String deleteWallet = "wallet.tronlink.harmony:id/delete";
    public  String deleteWallet2 = "wallet.tronlink.harmony:id/delete2";
    public  String deleteWalletTop = "wallet.tronlink.harmony:id/delete_top";
    public  String etPassword = "wallet.tronlink.harmony:id/et_password";
    public  String backupMnemonic = "wallet.tronlink.harmony:id/rl_mnemonic";
    public  String backupPrivateKey = "wallet.tronlink.harmony:id/rl_privatekey";
    public  String backupKeystore = "wallet.tronlink.harmony:id/rl_keystore";
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
        desiredCapabilities.setCapability("language", "zh");
        desiredCapabilities.setCapability("locale", "CN");
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
            if (str.equals("List")||str.equals("of")||str.equals("devices")||str.equals("attached")||str.equals("device")||str.equals("unauthorized")){
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
            if (resId.indexOf("com.tronlinkpro.wallet:id") != -1){
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
        File screenFile = new File("build/reports/tests/android.com.tronlink/screenShot/"+date+description+".png");
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
            if (elementIdOrXPath.indexOf("com.tronlinkpro.wallet:id") != -1){
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
            Runtime.getRuntime().exec(adb +" push "+imgUrl+" storage/sdcard0/android.com.tronlink/111" + name +".png");
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

