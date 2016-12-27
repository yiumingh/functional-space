(ns functional-space.core
  (:require [functional-space.web :refer [web-app]]
            [mount.core :as mount]))

(defn -main []
  (mount/start #'web-app))
