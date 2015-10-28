# RecyclerViewGenericAdapterSample


include this library through build.gradle using theses lines :

    repositories {
      ...
      maven { url 'https://dl.bintray.com/lucasfm/maven/' }
    }
    dependencies {
    compile 'com.lfm.rvgenadapter:rvgenadapter:1.0.3'
    }

How to use it:
Implement the ViewPresenter class and use it as a parameter of the GenericRecylerAdapter :

      sampleAdapter = new GenericRecyclerAdapter<>(this, sampleDatas, SampleItemPresenter.class, onSampleItemClickListener, params);
      sampleRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
      sampleRecyclerView.setAdapter(sampleAdapter);

See the sample code for additionnal informations :
https://github.com/LucasFoulonMongai/RecyclerViewGenericAdapterSample/tree/master/app/src/main/java/com/lfm/recyclerviewgenericadaptersample
