package com.seongbinsoft.pickupasap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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

import com.bumptech.glide.Glide;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.util.Utility;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class Login_Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_login_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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

    }//onViewCreated method...

}//LoginFragment...