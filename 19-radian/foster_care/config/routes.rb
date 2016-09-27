Rails.application.routes.draw do
  devise_for :users
  root 'facilities#index'
  resources :facilities, only: [:index]
  resources :users, only: [:show, :edit, :update]
  resources :messages
end
