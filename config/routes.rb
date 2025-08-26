Rails.application.routes.draw do
  devise_for :users, controllers: { registrations: "users/registrations" }
  get "up" => "rails/health#show", as: :rails_health_check

  root "home#index"

  resources :stores, except: [ :index ] do
    resources :products, except: [ :index ]
  end

  resources :stores do
    resource :address, only: [ :new, :create, :edit, :update ]
  end

  resources :products, only: [ :index ]

  resources :chats, only: [ :create, :show ] do
    resources :messages, only: [ :create ]
  end

  resources :profiles, except: [ :index ] do
    resources :posts, only: [ :create ]
  end

  resources :posts, except: [ :create ] do
    resources :reactions, only: [ :create ]
    resources :comments, only: [ :create ]
  end
end
