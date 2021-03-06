package xyz.fullstackahead.where2go.injection

import dagger.Component
import xyz.fullstackahead.where2go.ui.activity.MainActivity
import xyz.fullstackahead.where2go.Where2GoApp
import xyz.fullstackahead.where2go.ui.adapter.RecommendationsAdapter
import xyz.fullstackahead.where2go.ui.fragment.LoginDialogFragment
import xyz.fullstackahead.where2go.ui.fragment.SearchDialogFragment
import xyz.fullstackahead.where2go.ui.viewmodel.LandingViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class
))
interface MainComponent {
    fun inject(app: Where2GoApp)
    fun inject(activity: MainActivity)
    fun inject(loginFragment: LoginDialogFragment)
    fun inject(viewModel: LandingViewModel)
    fun inject(adapter: RecommendationsAdapter)
    fun inject(searchFragmentFragment: SearchDialogFragment)
}
