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

  resources :products, only: [ :show ] do
    resources :comments, only: [ :create ]
  end

  resources :products, only: [ :show ] do
    resources :reactions, only: [ :create ]
  end
end
