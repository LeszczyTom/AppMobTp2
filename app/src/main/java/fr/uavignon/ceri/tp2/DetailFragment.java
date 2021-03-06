package fr.uavignon.ceri.tp2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import fr.uavignon.ceri.tp2.data.Book;

public class DetailFragment extends Fragment {

    private EditText textTitle, textAuthors, textYear, textGenres, textPublisher;
    private Book tmp;
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
        viewModel.getBookById(args.getBookNum());

        textTitle =  view.findViewById(R.id.nameBook);
        textAuthors =  view.findViewById(R.id.editAuthors);
        textYear =  view.findViewById(R.id.editYear);
        textGenres =  view.findViewById(R.id.editGenres);
        textPublisher =  view.findViewById(R.id.editPublisher);

        Observer<Book> book = book1 -> {
            textTitle.setText(book1.getTitle());
            textAuthors.setText(book1.getAuthors());
            textYear.setText(book1.getYear());
            textGenres.setText(book1.getGenres());
            textPublisher.setText(book1.getPublisher());
            tmp = book1;
        };

        viewModel.getBook().observe(getViewLifecycleOwner(), book );

        view.findViewById(R.id.buttonBack).setOnClickListener(view1 -> NavHostFragment.findNavController(DetailFragment.this)
                .navigate(R.id.action_SecondFragment_to_FirstFragment));

        view.findViewById(R.id.buttonUpdate).setOnClickListener(view12 -> {
            tmp.setTitle(textTitle.getText().toString());
            tmp.setYear(textYear.getText().toString());
            tmp.setPublisher(textPublisher.getText().toString());
            tmp.setGenres(textGenres.getText().toString());
            tmp.setAuthors(textAuthors.getText().toString());
            viewModel.modifyBook(tmp);
        });
    }
}