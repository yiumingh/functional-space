(defproject functional-space "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.293"]
                 [ring "1.5.0"]
                 [compojure "1.5.1"]
                 [mount "0.1.11"]
                 [hiccup "1.0.5"]
                 [reagent "0.6.0"]
                 [reagent-utils "0.2.0"]
                 [garden "1.3.2"]
                 [jayq "2.5.4"]
                 [secretary "1.2.3"]]
  :plugins [[lein-figwheel "0.5.8"]]
  :source-paths ["src/clj" "src/cljc"]
  :main functional-space.core
  :cljsbuild {
              :builds [{:id "frontend"
                        :source-paths ["src/cljs/functional_space"]
                        :figwheel true
                        :compiler {:main "functional-space.core"
                                   :asset-path "public/js/frontend"
                                   :output-to "resources/public/js/frontend.js"
                                   :output-dir "resources/public/js/frontend"}}
                       {:id "admin"
                        :source-paths ["src/cljs/functional_space_admin"]
                        :figwheel true
                        :compiler {:main "functional-space-admin.core"
                                   :asset-path "public/js/admin"
                                   :output-to "resources/public/js/admin.js"
                                   :output-dir "resources/public/js/admin"}}]})
