# RecyclerViewGenericAdapterSample


Include this library through build.gradle using theses lines :

    repositories {
        maven { url 'https://dl.bintray.com/lucasfm/maven/' }
    }
    dependencies {
        compile 'com.lfm.rvgenadapter:rvgenadapter:1.2.0'
    }

How to use it:
Implement the ItemViewHolder class, create an ItemViewHolderFactory for it and use it as a parameter of the GenericAdapter :

      adapter = new GenericAdapter<>(this, SampleItemViewHolder.FACTORY, this);
      adapter.setItems(sampleModels)
      recyclerView.setAdapter(adapter);
      
For multiples item types, you can also use the MultiGenericAdapter :
      
      MultiGenericAdapter multiAdapter = new MultiGenericAdapter(this, this);
      multiAdapter.setItems(0, 0, "Title", TitleItemViewHolder.FACTORY);
      multiAdapter.setItems(1, 1, sampleModels, SampleItemViewHolder.FACTORY);
      recyclerView.setAdapter(adapter);

See the sample code for additionnal informations :
https://github.com/LucasFoulonMongai/RecyclerViewGenericAdapterSample/tree/master/app/src/main/java/com/lfm/recyclerviewgenericadaptersample
