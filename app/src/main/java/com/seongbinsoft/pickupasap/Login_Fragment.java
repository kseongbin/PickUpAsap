package com.seongbinsoft.pickupasap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.util.Utility;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;


import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class Login_Fragment extends Fragment {

    AdView adView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_login_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Google Admob 배너광고
        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull @NotNull InitializationStatus initializationStatus) {
                //Toast.makeText(getContext(), "이니셜 완료!", Toast.LENGTH_SHORT).show();
            }
        });
        adView = view.findViewById(R.id.adv);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        //카카오톡 키 해쉬 얻어오기
        String keyHash= Utility.INSTANCE.getKeyHash(getContext());
        Log.i("KeyHash", keyHash);

        //카카오톡 로그인
        ImageView loginkakao = view.findViewById(R.id.btn_kakao);
        loginkakao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserApiClient.getInstance().loginWithKakaoAccount(getContext(), new Function2<OAuthToken, Throwable, Unit>() {
                    @Override
                    public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                        if (oAuthToken!=null){
                            Toast.makeText(getContext(), "로그인 성공!", Toast.LENGTH_SHORT).show();

                            UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                                @Override
                                public Unit invoke(User user, Throwable throwable) {
                                    if (user!=null){
                                        G.login_id = user.getId();
                                        G.login_nickname = user.getKakaoAccount().getProfile().getNickname();
                                        G.profileUrl = user.getKakaoAccount().getProfile().getProfileImageUrl();

                                        Intent intent = new Intent(getContext(), LoginAccountActivity.class);
                                        intent.putExtra("nickname", G.login_nickname);
                                        intent.putExtra("profileUrl", G.profileUrl);
                                        startActivity(intent);
                                    }
                                    return null;
                                }
                            });
                        }else {
                            Toast.makeText(getContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                        }
                        return null;
                    }
                });
            }//kakaoOnClick method...
        });//kakaoOnClickListener method..

//        SignInButton googleBtn = view.findViewById(R.id.btn_google);
//        final int RC_SIGN_IN = 1;
//        GoogleSignInClient googleSignInClient;
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//        googleLoginHelper();
//        googleBtn.setOnClickListener(this::googleClick);
//
//        public void googleLoginHelper(){
//            GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
//                    .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                    .requestIdToken(getString(R.string.default_web_client_id))
//                    .requestEmail()
//                    .build();
//
//            googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);
//        }
//
//        public void googleClick(View view) {
//            Intent signInIntent = googleSignInClient.getSignInIntent();
//            startActivityForResult(signInIntent,RC_SIGN_IN);
//        }



//        //네이버 로그인
//        ImageView loginNaver = view.findViewById(R.id.btn_naver);
//        loginNaver.setOnClickListener(new View.OnClickListener() {
//            OAuthLogin mOAuthLoginModule;
//            @Override
//            public void onClick(View v) {
//                mOAuthLoginModule = OAuthLogin.getInstance();
//                mOAuthLoginModule.init(getContext(), getString(R.string.naver_client_id), getString(R.string.naver_client_secret), getString(R.string.naver_client_name));
//
//                OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
//                    @Override
//                    public void run(boolean success) {
//                        if (success){
//                            String accessToken = mOAuthLoginModule.getAccessToken(getContext());
//                            String refreshToken = mOAuthLoginModule.getRefreshToken(getContext());
//                            String tokenType = mOAuthLoginModule.getTokenType(getContext());
//                            long expiresAt = mOAuthLoginModule.getExpiresAt(getContext());
//
//                            Log.i("LoginData", "accessToken: " + accessToken);
//                            Log.i("LoginData", "refreshToken: " + refreshToken);
//                            Log.i("LoginData", "expiresAt: " + expiresAt);
//                            Log.i("LoginData", "tokenType: " + tokenType);
//                        }else {
//                            String errorCode = mOAuthLoginModule.getLastErrorCode(getContext()).getCode();
//                            String errorDesc = mOAuthLoginModule.getLastErrorDesc(getContext());
//                            Toast.makeText(getContext(), "errorCode: "+ errorCode + ", errorDesc: " + errorDesc, Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                };
//                mOAuthLoginModule.startOauthLoginActivity(getActivity(), mOAuthLoginHandler);
//            }//naverOnclick method...
//        });//naverOnclickListener method..

    }//onViewCreated method...

}//LoginFragment...