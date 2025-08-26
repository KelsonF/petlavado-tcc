class ProfilesController < ApplicationController
  before_action :set_profile, only: %i[ show ]

  def show;end

  def new
    @profile = current_user.build_profile
  end

  def create
    @profile = current_user.profile(profile_params)

    if @profile.save
      redirect_to @profile, notice: "Perfil criado com sucesso"
    else
      render :new, status: :unprocessable_entity
    end
  end

  def edit;end

  def update
    if @profile.update(profile_params)
      redirect_to @profile, notice: "Perfil atualizado com sucesso"
    else
      render :edit, status: :unprocessable_entity
    end
  end

  def destroy
    @profile.destroy
    redirect_to root_path, notice: "Perfil excluído com sucesso"
  end

  private

  def set_profile
    @profile = Profile.find(params[:profile_id])
  end

  def profile_params
    params.require(:profile).permit(:bio)
  end

  def validates_profile_ownership
    unless @profile.user == current_user
      redirect_to root_path, alert: "Você não tem permissão para realizar esta ação."
    end
  end
end
