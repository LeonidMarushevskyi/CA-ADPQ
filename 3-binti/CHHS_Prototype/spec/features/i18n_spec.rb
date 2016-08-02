require "rails_helper"

feature "Viewing site en Español" do
  it "allows user to toggle back and forth between English and Spanish" do
    visit root_path

    expect(page.current_path).to eq("/")
    expect(page).to have_content("Locate foster care service providers near you")
    expect(page).to have_no_content("Localiza proveedor de servicios de"\
      " cuidado temporal cerca de usted")

    click_link "Español"

    expect(page.current_path).to match("/es")
    expect(page).to have_no_content("Locate foster care service providers near you")
    expect(page).to have_content("Localiza proveedor de servicios de"\
      " cuidado temporal cerca de usted")

    click_link "English"

    expect(page.current_path).to eq("/")
    expect(page).to have_content("Locate foster care service providers near you")
    expect(page).to have_no_content("Localiza proveedor de servicios de"\
      " cuidado temporal cerca de usted")
  end
end
