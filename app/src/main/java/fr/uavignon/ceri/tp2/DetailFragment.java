package fr.uavignon.ceri.tp2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import fr.uavignon.ceri.tp2.data.Book;

public class DetailFragment extends Fragment {

    private EditText textTitle, textAuthors, textYear, textGenres, textPublisher;
    private Book tmp;
    private long id;
    DetailViewModel viewModel;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DetailViewModel.class);

        // Get selected book
        DetailFragmentArgs args = DetailFragmentArgs.fromBundle(getArguments());
        id = args.getBookNum();
        viewModel.getBookById(id);

        Button button = view.findViewById(R.id.buttonUpdate);
        textTitle =  view.findViewById(R.id.nameBook);
        textAuthors =  view.findViewById(R.id.editAuthors);
        textYear =  view.findViewById(R.id.editYear);
        textGenres =  view.findViewById(R.id.editGenres);
        textPublisher =  view.findViewById(R.id.editPublisher);

        Observer<Book> book = book1 -> {
            if (id != -1) {
                textTitle.setText(book1.getTitle());
                textAuthors.setText(book1.getAuthors());
                textYear.setText(book1.getYear());
                textGenres.setText(book1.getGenres());
                textPublisher.setText(book1.getPublisher());
                tmp = book1;
            }
            else {
                button.setText("Ajouter");
            }
        };

        viewModel.getBook().observe(getViewLifecycleOwner(), book );

        view.findViewById(R.id.buttonBack).setOnClickListener(view1 -> NavHostFragment.findNavController(DetailFragment.this)
                .navigate(R.id.action_SecondFragment_to_FirstFragment));

        view.findViewById(R.id.buttonUpdate).setOnClickListener(view12 -> {
            if (id != -1) {
                tmp.setTitle(textTitle.getText().toString());
                tmp.setYear(textYear.getText().toString());
                tmp.setPublisher(textPublisher.getText().toString());
                tmp.setGenres(textGenres.getText().toString());
                tmp.setAuthors(textAuthors.getText().toString());
            }
            else {
                tmp = new Book(textTitle.getText().toString(), textYear.getText().toString(),
                        textPublisher.getText().toString(), textGenres.getText().toString(),
                        textAuthors.getText().toString());
            }
            String title = textTitle.getText().toString();
            String year = textYear.getText().toString();
            String publisher = textPublisher.getText().toString();
            String genre = textGenres.getText().toString();
            String author = textAuthors.getText().toString();

            if (!title.equals("") && !year.equals("") && !publisher.equals("") && !genre.equals("") && !author.equals("")) {
                viewModel.insertOrUpdate(tmp);
                Snackbar.make(view, title + " ajouté", Snackbar.LENGTH_LONG).show();
            }
            else{
                Snackbar.make(view, "Information incomplete ", Snackbar.LENGTH_LONG).show();
            }



        });
    }
}