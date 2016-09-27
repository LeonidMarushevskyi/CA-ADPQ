using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using AutoMapper;
using Com.Natoma.HhsPrototype.UserInterface.Models;
using Com.Natoma.HhsPrototype.UserInterface.HhsPrototypeProc;

namespace Com.Natoma.HhsPrototype.UserInterface
{
    public static class AutoMapperConfig
    {
        public static void RegisterAllMapping()
        {
            InitializeUserProfileMapping();
            InitializeMessageMapping();
        }

        public static void InitializeUserProfileMapping()
        {
            AutoMapper.Mapper.CreateMap<HhsPrototypeProc.UserProfile, UserProfileModel>()
            .ForMember(dest => dest.IsCellPhone, opts => opts.Ignore());

            AutoMapper.Mapper.CreateMap<UserProfileModel, HhsPrototypeProc.UserProfile>()
                .ForMember(dest => dest.ExtensionData, opts => opts.Ignore());
        }

        public static void InitializeMessageMapping()
        {
            AutoMapper.Mapper.CreateMap<HhsPrototypeProc.Message, MessageModel>()
                .ForMember(dest => dest.RecipientName, opts => opts.Ignore())
                .ForMember(dest => dest.SenderName, opts => opts.Ignore())
                .ForMember(dest => dest.IsChecked, opts => opts.Ignore());

            AutoMapper.Mapper.CreateMap<MessageModel, HhsPrototypeProc.Message>()
                .ForMember(dest => dest.ExtensionData, opts => opts.Ignore());
        }
    }
}