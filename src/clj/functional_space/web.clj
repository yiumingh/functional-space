(ns functional-space.web
  (:require [mount.core :refer [defstate]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [resources]]
            [ring.adapter.jetty :refer [run-jetty]]
            [hiccup.page :refer [html5 include-js include-css]]))

(defn homepage []
  (html5 {:lang "en"}
         [:head
          [:title "A functional space"]
          (include-css "/public/css/bootstrap.min.css")
          (include-css "/public/css/fancy-sidebar.css")
          (include-js "https://cdn.jsdelivr.net/jquery/3.1.1/jquery.min.js")
          (include-js "/public/js/bootstrap.js")
          (include-js "/public/js/sidebar.js")
          [:body
           [:div#wrapper
            [:div.overlay]
            [:div#navbar]
            [:div#page-content-wrapper
             [:button.is-closed.hamburger {:type "button"
                                           :data-toggle "offcanvas"}
              [:span.hamb-top]
              [:span.hamb-middle]
              [:span.hamb-bottom]]
             [:div#app]]]]
          (include-js "/public/js/frontend.js")]))

(defroutes app
           (GET "/" [] (homepage))
           (GET "/admin" [] "Admin")
           (resources "/public"))

(defn start-fs-web []
  (run-jetty app {:port 8000 :join? false}))

(defstate web-app :start (start-fs-web)
                  :stop (.stop web-app))
