package info.upump.wimmelbuch.loader;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;


/**
 * Created by explo on 18.01.2018.
 */

public interface PrestaShopClient {

    @GET("11?ws_key=YEXP3668Z18KYA6NJT7B9CPE1DFAK7U")
    Call<List<Prestashop>> reposForUser(
            //@Path("user") String user
    );

    @GET("/api/products")
    Call<ResponseBody> reposForUser2(

    );
    @Headers({"Accept:application/xml"})
    @GET("/api/products")
    Call<Prestashop> reposForUser3(

    );
    @Headers({"Accept: application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8","Content-Type: application/xml;charset=utf-8"})
    @GET("/api/products/10?ws_key=YEXP3668Z18KYA6NJT7B9CPE1DFAK7U2")
    Call<ResponseBody> reposForUser4(

    );
}


