// AppModule.kt

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(TMDBService::class.java) }

    single { MovieRepository(get()) }
}

// MovieRepository.kt

class MovieRepository(private val tmdbService: TMDBService) {

    fun getPopularMovies(): LiveData<List<Movie>> {
        val movieData = MutableLiveData<List<Movie>>()
        tmdbService.getPopularMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                if (response.isSuccessful) {
                    movieData.value = response.body()?.movies
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                // handle failure
            }
        })
        return movieData
    }
}

// MovieViewModel.kt

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    val popularMovies: LiveData<List<Movie>> = repository.getPopularMovies()
}

// MovieFragment.kt

class MovieFragment : Fragment() {

    private val viewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = MovieAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.popularMovies.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        return view
    }
}
