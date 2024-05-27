//package com.luv2code.springboot.demo.mycoolapp;
//
//import org.springframework.data.elasticsearch.annotations.Document;
//
//import java.util.List;
//
//@Document(indexName = "movies")
//public class Games {
//    long id;
//    String slug;
//
//    String name;
//
//    String background_image;
//
//    Float rating;
//
//    Integer rating_top;
//
//    List<Games.Ratings> ratings;
//
//    Long rating_count;
//    Long reviews_text_count;
//
//    String updated;
//
//    Long reviews_count;
//
//    List<Games.Tags> tags;
//
//    List<Games.Screenshots> short_screenshots;
//
//    Games.ESRB_Rating esrb_rating;
//
//    List<Games.Stores> stores;
//
//    public void setStores(List<Stores> stores) {
//        this.stores = stores;
//    }
//
//    public List<Stores> getStores() {
//        return stores;
//    }
//
//    public void setEsrb_rating(ESRB_Rating esrb_rating) {
//        this.esrb_rating = esrb_rating;
//    }
//
//    public ESRB_Rating getEsrb_rating() {
//        return esrb_rating;
//    }
//
//    public void setShort_screenshots(List<Screenshots> short_screenshots) {
//        this.short_screenshots = short_screenshots;
//    }
//
//    public List<Screenshots> getShort_screenshots() {
//        return short_screenshots;
//    }
//
//    public void setTags(List<Tags> tags) {
//        this.tags = tags;
//    }
//
//    public List<Tags> getTags() {
//        return tags;
//    }
//
//    public void setRating(Float rating) {
//        this.rating = rating;
//    }
//
//    public void setRating_count(Long rating_count) {
//        this.rating_count = rating_count;
//    }
//
//    public void setRating_top(Integer rating_top) {
//        this.rating_top = rating_top;
//    }
//
//    public void setRatings(List<Ratings> ratings) {
//        this.ratings = ratings;
//    }
//
//    public void setReviews_count(Long reviews_count) {
//        this.reviews_count = reviews_count;
//    }
//
//    public void setReviews_text_count(Long reviews_text_count) {
//        this.reviews_text_count = reviews_text_count;
//    }
//
//    public void setUpdated(String updated) {
//        this.updated = updated;
//    }
//
//    public Float getRating() {
//        return rating;
//    }
//
//    public Integer getRating_top() {
//        return rating_top;
//    }
//
//    public List<Ratings> getRatings() {
//        return ratings;
//    }
//
//    public Long getRating_count() {
//        return rating_count;
//    }
//
//    public Long getReviews_count() {
//        return reviews_count;
//    }
//
//    public Long getReviews_text_count() {
//        return reviews_text_count;
//    }
//
//    public String getUpdated() {
//        return updated;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public String getBackground_image() {
//        return background_image;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getSlug() {
//        return slug;
//    }
//
//    public void setBackground_image(String background_image) {
//        this.background_image = background_image;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setSlug(String slug) {
//        this.slug = slug;
//    }
//
//    @Override
//    public String toString() {
//        return "Movie{" +
//                "id=" + id +
//                ", slug='" + slug + '\'' +
//                ", name='" + name + '\'' +
//                ", background_image='" + background_image + '\'' +
//                '}';
//    }
//
//    public static class Stores{
//        Integer id;
//        Games.Stores.Store store;
//
//        public void setId(Integer id) {
//            this.id = id;
//        }
//
//        public void setStore(Store store) {
//            this.store = store;
//        }
//
//        public Integer getId() {
//            return id;
//        }
//
//        public Store getStore() {
//            return store;
//        }
//
//        public static class Store{
//            Integer id;
//            String name;
//            String slug;
//            String domain;
//            Long game_count;
//            String image_background;
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public void setId(Integer id) {
//                this.id = id;
//            }
//
//            public void setSlug(String slug) {
//                this.slug = slug;
//            }
//
//            public void setImage_background(String image_background) {
//                this.image_background = image_background;
//            }
//
//            public void setDomain(String domain) {
//                this.domain = domain;
//            }
//
//            public void setGame_count(Long game_count) {
//                this.game_count = game_count;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public String getSlug() {
//                return slug;
//            }
//
//            public Integer getId() {
//                return id;
//            }
//
//            public String getImage_background() {
//                return image_background;
//            }
//
//            public Long getGame_count() {
//                return game_count;
//            }
//
//            public String getDomain() {
//                return domain;
//            }
//        }
//    }
//    public static class ESRB_Rating{
//        Integer id;
//        String name;
//        String slug;
//
//        public void setId(Integer id) {
//            this.id = id;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public void setSlug(String slug) {
//            this.slug = slug;
//        }
//
//        public Integer getId() {
//            return id;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public String getSlug() {
//            return slug;
//        }
//    }
//    public static class Screenshots{
//        Integer id;
//
//        String image;
//
//        public void setId(Integer id) {
//            this.id = id;
//        }
//
//        public void setImage(String image) {
//            this.image = image;
//        }
//
//        public Integer getId() {
//            return id;
//        }
//
//        public String getImage() {
//            return image;
//        }
//    }
//    public static class Ratings{
//        Integer id;
//
//        String title;
//
//        Long count;
//
//        Float percent;
//
//        public void setId(Integer id) {
//            this.id = id;
//        }
//
//        public void setCount(Long count) {
//            this.count = count;
//        }
//
//        public void setPercent(Float percent) {
//            this.percent = percent;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public Float getPercent() {
//            return percent;
//        }
//
//        public Integer getId() {
//            return id;
//        }
//
//        public Long getCount() {
//            return count;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        @Override
//        public String toString() {
//            return "Ratings{" +
//                    "id=" + id +
//                    ", title='" + title + '\'' +
//                    ", count=" + count +
//                    ", percent=" + percent +
//                    '}';
//        }
//    }
//
//    public static class Tags{
//        Long id;
//
//        String name;
//        String slug;
//        String language;
//        String image_background;
//
//        Long games_count;
//
//        public void setSlug(String slug) {
//            this.slug = slug;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public void setId(Long id) {
//            this.id = id;
//        }
//
//        public void setGames_count(Long games_count) {
//            this.games_count = games_count;
//        }
//
//        public void setImage_background(String image_background) {
//            this.image_background = image_background;
//        }
//
//        public void setLanguage(String language) {
//            this.language = language;
//        }
//
//        public String getSlug() {
//            return slug;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public Long getId() {
//            return id;
//        }
//
//        public Long getGames_count() {
//            return games_count;
//        }
//
//        public String getImage_background() {
//            return image_background;
//        }
//
//        public String getLanguage() {
//            return language;
//        }
//    }
//}
//
//
