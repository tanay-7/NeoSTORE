package com.example.neostore.ui.mvp.login

import com.example.neostore.network.Api
import com.example.neostore.network.RetrofitClient
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LoginPresenter : LoginContract.LoginPresenter {

    lateinit var apiService: Api
    lateinit var results: LoginResponse
    var loginView: LoginContract.LoginView

    constructor(loginView: LoginContract.LoginView) {
        this.loginView = loginView
    }

    override fun login(email: String, password: String) {
        // Api Call

        val userObserver: Observer<LoginResponse> = getLoginObserver()
        apiService = RetrofitClient.getInstance().getClient().create(Api::class.java)
        apiService.userLogin(email, password)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(userObserver)
    }

    private fun getLoginObserver(): Observer<LoginResponse> {
        return object : Observer<LoginResponse> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: LoginResponse) {
                results = t
                loginView.showLoginSuccess(results.message)
            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }
        }
    }
}


//Callback Code

/*APIManager().login(email, password, object : APICallback<LoginResponse>() {
          override fun onCustomResponse(statusCode: Int?, response: LoginResponse?) {
              var results = response
              loginView.showLoginSuccess(results!!.message)
          }
      })*/

//End


//Retrofit Code without bypassing it through interface

/*apiService = RetrofitClient.provideRetro().create(Api::class.java)
apiService.userLogin(email, password).enqueue(
    object : Callback<LoginResponse> {
        override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
            loginView.showLoginError()
        }

        override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>) {
            if (response.isSuccessful) {
                var results = response.body()
                loginView.showLoginSuccess(results!!.message)
            }
        }
    }
)*/

//End