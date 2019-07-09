(ns clj-loopback.handler
  (:require [compojure.core :refer [defroutes routes]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body wrap-json-params]]
            [ring.util.response :refer [response]]
            [hiccup.middleware :refer [wrap-base-url]]
            [compojure.handler :as handler]
            [compojure.route :as route]))

(defn init []
  (println "clj-loopback is starting"))

(defn destroy []
  (println "clj-loopback is shutting down"))


(defn handle-loopback [req]
  (response (dissoc req :body)))

(def app
  (-> handle-loopback
      wrap-json-response
      (handler/site)
      (wrap-base-url)))
