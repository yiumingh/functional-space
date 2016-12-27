(ns functional-space.core
  (:require [reagent.core :as r]
            [reagent.session :as session]
            [goog.events :as events]
            [goog.history.EventType :as HistoryEventType]
            [secretary.core :as secretary :include-macros true]
            [garden.core :refer [css]])
  (:import goog.History))

(defn sidebar-nav []
  [:nav.navbar.navbar-inverse.navbar-fixed-top {:id "sidebar-wrapper"
                                                :role "navigation"}
   [:ul.nav.sidebar-nav
    [:li
     [:a {:href "#"}
      [:h1 {:dangerouslySetInnerHTML {:__html "&#955; space"}}]]]
    [:li
     [:a {:href "#"} "Top story"]]]])

(defn home-page []
  [:div.container
   [:div.row
    [:div.col-xs-12
     [:h1 "Hello, functional space!"]
     [:div {:style {:display :inline-block}}
      [:small {:style {:padding-right 10}} [:a {:href "#"} "Yiu Ming Huynh"]]
      [:small "November 21, 2016 19:47:58 CST"]]
     [:hr]
     [:p "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In fringilla nibh velit, facilisis finibus metus pellentesque eu. Nam vehicula, augue vel sodales suscipit, lectus urna fermentum sapien, sed eleifend nisl dui eget purus. Aliquam erat volutpat. Proin lobortis dolor in dui mollis dignissim. Nunc in nisl est. Aenean consectetur porta lobortis. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nam sem nisl, sollicitudin id nunc et, rutrum blandit neque.\n\nSuspendisse finibus enim augue, eget dignissim ex accumsan et. Nulla elementum neque et est ornare, quis hendrerit nunc convallis. Nullam in felis tortor. Curabitur et est id purus tincidunt aliquet ac eu arcu. Morbi tincidunt velit venenatis, porta risus at, facilisis tortor. Suspendisse quam erat, mattis non dui vitae, ornare ultricies odio. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut eget ex vehicula, maximus velit quis, suscipit orci. Phasellus aliquam mi blandit nisl tempus maximus. Proin molestie, ante non rhoncus interdum, nulla ligula interdum felis, sed porttitor sapien orci et ante. Nunc mollis mattis odio, ut porta elit posuere eget. Maecenas non enim ex. Nunc sollicitudin tellus et tortor vehicula, quis tincidunt nisi imperdiet. Donec interdum convallis blandit.\n\nInteger luctus quam sodales feugiat tincidunt. Aenean vestibulum lectus sed tincidunt commodo. Proin pretium ante quis lacus elementum, a tristique eros tincidunt. Vestibulum leo urna, faucibus et pulvinar ut, tempus in dui. Morbi ac semper est. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In id tristique sapien. Mauris vitae ante pulvinar dolor sollicitudin facilisis. Aenean semper ante molestie venenatis auctor. Donec non eros erat. Donec eu rutrum purus. Vivamus molestie dolor nisl, rutrum tincidunt ex euismod vitae. Pellentesque eget leo rutrum, ultricies velit luctus, cursus sapien"]]]])

(def pages
  {:home #'home-page})

(defn page []
  [(pages (session/get :page))])

(secretary/set-config! :prefix "#")

(secretary/defroute "/" [] (session/put! :page :home))

(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
      HistoryEventType/NAVIGATE
      (fn [event]
        (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defn mount-components []
  (r/render [#'page] (.getElementById js/document "app"))
  (r/render [#'sidebar-nav] (.getElementById js/document "navbar")))

(defn init! []
  (hook-browser-navigation!)
  (mount-components))

(init!)